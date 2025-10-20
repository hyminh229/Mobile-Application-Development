package hy.uth.tuan04bai2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmScreen(email: String?, code: String?, password: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Confirm", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("We are here to help you!")
        Spacer(modifier = Modifier.height(24.dp))
        Text("Email: $email")
        Text("Code: $code")
        Text("Password: $password")
    }
}
