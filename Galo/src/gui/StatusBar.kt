package gui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.*

val BAR_HEIGHT = 32.dp

@Composable
fun StatusBar(state: GameState) = Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
) {
    val [text,player] = when(state){
        is Win -> "Winner: " to state.winner
        is Draw -> "Draw" to null
        is Run -> "Turn: " to state.turn
    }
    Text(text, style = MaterialTheme.typography.displaySmall)
    player?.let{ Cell(player, Modifier.size(BAR_HEIGHT)) }
}