package console

import model.*

private val separator = "---"+"+---".repeat(BOARD_SIZE-1)

fun Game.show() {
    Position.all.chunked(BOARD_SIZE).forEach { row ->
        println(row.joinToString(" | "," "){ board[it].toChar().toString() })
        if (row[0].row <BOARD_SIZE-1) println(separator)
    }
    val status = when(state){
        is Win -> "Winner: ${state.winner.toChar()}"
        is Draw -> "Draw"
        is Run -> "Turn: ${state.turn.toChar()}"
    }
    println(status)
}

fun Player?.toChar() = when(this) {
    Player.X -> 'X'
    Player.O -> '0'
    null -> ' '
}