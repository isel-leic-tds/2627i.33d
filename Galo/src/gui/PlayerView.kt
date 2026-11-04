package gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.mandatorySystemGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Player
import org.jetbrains.compose.resources.painterResource
import galo.generated.resources.*

@Composable
fun PlayerView(
    player: Player?,
    modifier: Modifier = Modifier.size(150.dp),
    onClick: () -> Unit = {}
) {
    if (player == null) Box(modifier)
    else {
        val resource = when (player) {
            Player.X -> Res.drawable.cross
            Player.O -> Res.drawable.circle
        }
        Image(
            painterResource(resource),
            contentDescription = null,
            modifier = modifier.clickable(onClick = onClick)
        )
    }
}