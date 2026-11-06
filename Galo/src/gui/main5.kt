package gui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import model.*

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(325.dp, 420.dp))
    ) {
        App5(onExit = ::exitApplication)
    }
}

@Composable
private fun FrameWindowScope.GaloMenu(
    onExit: () -> Unit,
    onNew: () -> Unit
) {
    MenuBar {
        Menu("Game") {
            Item("New", onClick = onNew)
            Item("Exit", onClick = onExit)
        }
    }
}

@Composable
fun FrameWindowScope.App5(onExit: () -> Unit) {
    var game by remember { mutableStateOf(Game()) }
    GaloMenu(onExit = onExit, onNew = { game = game.new() })
    MaterialTheme {
        Column {
            Board(game.board, onClick = { p:Position ->
                if (game.state is Run) game = game.play(p)
            })
            StatusBar(game.state)
        }
    }
}