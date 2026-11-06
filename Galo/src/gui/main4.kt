package gui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import model.*

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize.Unspecified)
    ) {
        App4()
    }
}

@Composable
fun App4() {
    var game by remember { mutableStateOf(Game()) }
    MaterialTheme {
        Column {
            Board(game.board, onClick = { p:Position ->
                if (game.state is Run) game = game.play(p)
            })
            StatusBar(game.state)
        }
    }
}