package hy.uth.tuan03.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import hy.uth.tuan03.R

import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ImageScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Images", onBack = onBack) {
        Column(Modifier.padding(16.dp)) {
            Text("From URL")
            Spacer(Modifier.height(8.dp))
            AsyncImage(
                model = "https://giaothongvantaihcm.edu.vn/wp-content/uploads/2025/01/logo-GTVT.png",
                contentDescription = "UTH logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text("In app")
            Spacer(Modifier.height(8.dp))
            Image(
                painter = painterResource(R.drawable.uth2),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ImageScreenPreview() {
    hy.uth.tuan03.ui.theme.Tuan03Theme {
        ImageScreen(onBack = {})
    }
}
