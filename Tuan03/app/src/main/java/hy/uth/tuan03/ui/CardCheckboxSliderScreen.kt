package hy.uth.tuan03.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardCheckboxSliderScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Card / Checkbox / Slider", onBack = onBack) {
        var checked by remember { mutableStateOf(false) }
        var value by remember { mutableStateOf(0.5f) }

        Column(Modifier.padding(16.dp)) {
            ElevatedCard(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("Card title", style = MaterialTheme.typography.titleMedium)
                    Text("This is a simple Material3 card.")
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = checked, onCheckedChange = { checked = it })
                Spacer(Modifier.width(8.dp))
                Text("I agree")
            }
            Spacer(Modifier.height(16.dp))
            Text("Slider value: ${"%.2f".format(value)}")
            Slider(value = value, onValueChange = { value = it })
        }
    }
}
