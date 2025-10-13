package hy.uth.tuan03.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowColumnScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Row Layout", onBack = onBack) {
        Column(Modifier.padding(16.dp)) {
            repeat(3) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    repeat(3) {
                        Box(
                            Modifier
                                .weight(1f)
                                .height(56.dp)
                                .background(Color(0xFFABC4FF))
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
