package hy.uth.tuan03.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hy.uth.tuan03.R
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun IntroScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.jetpackcomposelogo),
            contentDescription = null,
            modifier = Modifier.size(160.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text("Jetpack Compose", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("A modern UI toolkit for Android")
        Spacer(Modifier.height(20.dp))
        Button(onClick = onStart) { Text("I'm Ready") }
    }
}
@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    hy.uth.tuan03.ui.theme.Tuan03Theme {
        IntroScreen(onStart = {})
    }
}
