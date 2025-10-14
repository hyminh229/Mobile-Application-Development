package hy.uth.tuan03.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.dp


@Composable
fun TextScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Text Detail", onBack = onBack) {
        Column(Modifier.padding(16.dp)) {
            Text("The quick Brown fox jumps over the lazy dog.", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            Text("Bold text", fontWeight = FontWeight.Bold)
            Text("Italic text", fontStyle = FontStyle.Italic)
            Text("Underlined text", textDecoration = TextDecoration.Underline)
            Text("Strikethrough", textDecoration = TextDecoration.LineThrough)
            Text("Letter spacing", letterSpacing = androidx.compose.ui.unit.TextUnit.Unspecified)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TextScreenPreview() {
    hy.uth.tuan03.ui.theme.Tuan03Theme {
        TextScreen(onBack = {})
    }
}
