package hy.uth.tuan03.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonSwitchScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Button & Switch", onBack = onBack) {
        var enabled by remember { mutableStateOf(true) }
        var clickCount by remember { mutableStateOf(0) }

        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Switch(checked = enabled, onCheckedChange = { enabled = it })
                Spacer(Modifier.width(8.dp))
                Text(if (enabled) "Enabled" else "Disabled")
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = { clickCount++ }, enabled = enabled) {
                Text("Tap me ($clickCount)")
            }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = { clickCount = 0 }) { Text("Reset") }
        }
    }
}
