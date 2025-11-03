package hy.uth.btvntuan5

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.navigation.NavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val auth = Firebase.auth
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // TODO: Replace with your logo
            contentDescription = "UTH SmartTasks Logo",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("UTH SmartTasks", style = MaterialTheme.typography.headlineMedium)
        Text("A simple and efficient to-do app", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(32.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                // Sign out of Firebase to allow for account selection each time.
                auth.signOut()

                coroutineScope.launch {
                    isLoading = true
                    try {
                        val credentialManager = CredentialManager.create(context)

                        // 1. Build the request with Google ID option
                        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                            .setFilterByAuthorizedAccounts(false) // Show all Google accounts on the device.
                            .setServerClientId(context.getString(R.string.default_web_client_id))
                            .build()

                        val request: GetCredentialRequest = GetCredentialRequest.Builder()
                            .addCredentialOption(googleIdOption)
                            .build()

                        // 2. Show the sign-in prompt
                        val result = credentialManager.getCredential(context, request)
                        val credential = result.credential

                        // 3. Get the ID token and sign in with Firebase
                        val googleIdTokenCredential = when (credential) {
                            is CustomCredential -> {
                                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                                    GoogleIdTokenCredential.createFrom(credential.data)
                                } else {
                                    null
                                }
                            }
                            else -> null
                        }

                        if (googleIdTokenCredential != null) {
                            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                            auth.signInWithCredential(firebaseCredential).await()
                            navController.navigate("profile") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            throw IllegalStateException("Unexpected credential type")
                        }
                    // Handle cases where the user cancels the sign-in prompt
                    } catch (e: GetCredentialException) {
                        Toast.makeText(context, "Google Sign-In cancelled or failed.", Toast.LENGTH_SHORT).show()
                    // Handle other exceptions
                    } catch (e: Exception) {
                        Toast.makeText(context, "An error occurred: ${e.message}", Toast.LENGTH_LONG).show()
                    } finally {
                        isLoading = false
                    }
                }
            }) {
                Text("Sign in with Google")
            }
        }
    }
}
