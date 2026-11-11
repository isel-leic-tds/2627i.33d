package gui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Player
import model.Score

/** Apresenta a pontuação acumulada e bloqueia a janela do jogo até ser fechada. */
@Composable
fun ScoreInfo(score: Score, onClose: () -> Unit) = AlertDialog(
    onDismissRequest = onClose,
    title = { Text("Score") },
    text = {
        val style = MaterialTheme.typography.displaySmall
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(Modifier.padding(8.dp)) {
                Player.entries.forEach {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Cell(player = it, modifier = Modifier.size(32.dp))
                        Text(": ${score.getValue(it)}", style = style)
                    }
                }
            }
            Text("Draws: ${score.getValue(null)}", style = style)
        }
    },
    confirmButton = { TextButton(onClick = onClose) { Text("Close") } }
)
