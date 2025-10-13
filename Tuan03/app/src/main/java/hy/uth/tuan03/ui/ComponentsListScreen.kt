package hy.uth.tuan03.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hy.uth.tuan03.ui.navigation.Screen
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ComponentsListScreen(onOpen: (String) -> Unit, onBack: () -> Unit) {
    ScreenScaffold(title = "UI Components List", canNavigateBack = true, onBack = onBack) {
        Column(Modifier.padding(16.dp)) {
            val items = listOf(
                "Text" to Screen.Text.route,
                "Image" to Screen.Image.route,
                "TextField & PasswordField" to Screen.TextField.route,
                "Layout (Row & Column)" to Screen.Layout.route,
                "Button & Switch" to Screen.ButtonSwitch.route,
                "Card / Checkbox / Slider" to Screen.CardCheckboxSlider.route
            )
            items.forEach { (title, route) ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onOpen(route) }
                ) { Text(title, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp)) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsListPreview() {
    hy.uth.tuan03.ui.theme.Tuan03Theme {
        ComponentsListScreen(onOpen = {}, onBack = {})
    }
}
