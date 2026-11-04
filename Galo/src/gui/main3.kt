package gui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.Player
import model.other

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize.Unspecified)
    ) {
        App3()
    }
}

@Composable
fun App3() {
    var player by remember { mutableStateOf<Player?>(null) }
    MaterialTheme {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PlayerView(player, onClick = { player = player?.other })
            Button(onClick = {
                player = player?.other ?: Player.X }
            ) {
                Text("Other")
            }
            Button(onClick = { player = null }) { Text("Reset") }
        }
    }
}