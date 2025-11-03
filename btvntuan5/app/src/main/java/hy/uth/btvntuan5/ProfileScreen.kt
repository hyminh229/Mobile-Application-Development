
package hy.uth.btvntuan5

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen(navController: NavController) {
    val user = remember { Firebase.auth.currentUser }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val painter = rememberAsyncImagePainter(
            model = user?.photoUrl, 
            error = painterResource(id = R.drawable.ic_launcher_foreground) // Default avatar
        )
        Image(
            painter = painter,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: ${user?.displayName ?: "N/A"}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Email: ${user?.email ?: "N/A"}")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { 
            Firebase.auth.signOut()
            navController.navigate("login") {
                popUpTo("profile") { inclusive = true }
            } 
        }) {
            Text("Sign Out")
        }
    }
}
