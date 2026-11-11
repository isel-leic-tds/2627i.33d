package gui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import model.*

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(325.dp, 420.dp))
    ) {
        App(onExit = ::exitApplication)
    }
}

@Composable
fun FrameWindowScope.App(onExit: () -> Unit) {
    var game by remember { mutableStateOf(Game()) }
    var showScore by remember { mutableStateOf(false) }
    MenuBar {
        Menu("Game") {
            Item("New", enabled = !showScore, onClick = { game = game.new() })
            Item("Score", onClick = { showScore = true })
            Item("Exit", onClick = onExit)
        }
    }
    MaterialTheme {
        Column {
            Board(game.board, onClick = { p:Position ->
                if (game.state is Run) {
                    game = game.play(p)
                    if (game.state !is Run) showScore = true
                }
            })
            StatusBar(game.state)
        }
        if (showScore)
            ScoreInfo(score = game.score, onClose = { showScore = false })
    }
}
