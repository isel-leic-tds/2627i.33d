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
    val vm = remember { TextFileViewModel() /* MongoViewModel()*/ }
    MenuBar {
        Menu("Game") {
            Item("Start game", onClick = vm::start)
            Item("Join game", onClick = )
            Item("Refresh", onClick = )
            Item("New board", onClick = vm::new)
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
        vm.editName?.let{ EditNameDialog(it, onCancel = vm::cancelEdit, onAction = vm::action) }
    }
}
