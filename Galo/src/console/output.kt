package console

import model.*

fun Game.show() {
    board.chunked(3).forEachIndexed { idx, row ->
        println(row.joinToString(" | "," "))
        if (idx<2) println("---+---+---")
    }
    val status = when{
        isWinner('X') -> "Winner: X"
        isWinner('O') -> "Winner: O"
        EMPTY !in board -> "Draw"
        else -> "Turn: $turn"
    }
    println(status)
}