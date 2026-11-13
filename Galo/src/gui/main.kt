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
    val vm = remember { ViewModel() }
    MenuBar {
        Menu("Game") {
            Item("New", onClick = vm::new)
            Item("Score", onClick = vm::showScore)
            Item("Exit", onClick = onExit)
        }
    }
    MaterialTheme {
        Column {
            Board(vm.game.board, onClick = vm::play)
            StatusBar(vm.game.state)
        }
        if (vm.showScore)
            ScoreInfo(score = vm.game.score, onClose = vm::hideScore)
    }
}
