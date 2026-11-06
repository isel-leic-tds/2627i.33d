package gui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.*

val CELL_SIDE = 100.dp
val LINE_THICKNESS = 5.dp
val BOARD_SIDE = CELL_SIDE * BOARD_SIZE + LINE_THICKNESS*(BOARD_SIZE-1)

@Composable
fun Board(board: Board, onClick: (Position) -> Unit) = Column(
    Modifier.size(BOARD_SIDE).background(Color.Black),
    verticalArrangement = Arrangement.SpaceBetween
) {
    Position.all.chunked(BOARD_SIZE).forEach { row ->
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            row.forEach { pos ->
                Cell(
                    player = board[pos],
                    modifier = Modifier.size(CELL_SIDE).background(Color.White),
                    onClick= { onClick(pos) }
                )
            }
        }
    }
}