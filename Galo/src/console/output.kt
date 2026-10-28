package console

import model.*

private val separator = "---"+"+---".repeat(BOARD_SIZE-1)

fun Game.show() {
    board.chunked(BOARD_SIZE).forEachIndexed { idx, row ->
        println(row.joinToString(" | "," "){ it.toChar().toString() })
        if (idx<BOARD_SIZE-1) println(separator)
    }
    val status = when{
        isWinner(Player.X) -> "Winner: ${Player.X.toChar()}"
        isWinner(Player.O) -> "Winner: ${Player.O.toChar()}"
        EMPTY !in board -> "Draw"
        else -> "Turn: ${turn.toChar()}"
    }
    println(status)
}

fun Player?.toChar() = when(this) {
    Player.X -> 'X'
    Player.O -> '0'
    null -> ' '
}