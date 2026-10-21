package model

const val EMPTY = ' '

data class Game(
    val first: Char = 'X',
    val turn: Char = first,
    val board: List<Char> = [
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
    ],
)

fun Game.new() = Game(first= first.other)

val Char.other get() = if (this=='X') 'O' else 'X'

fun Game.canPlay(p: Int) = board[p]==EMPTY

fun Game.play(pos: Int) = Game(
    first = first,
    turn = turn.other,
    board = board.mapIndexed { i, p ->
        if (i==pos) turn else p
    }
)

fun Game.isWinner(p: Char) =
    (0..8 step 4).all{ board[it]==p } ||
    (2..6 step 2).all{ board[it]==p } ||
    (0..6 step 3).any{ r -> (0..2).all{board[r+it]==p} } ||
    (0..2).any{ c -> (0..6 step 3).all{board[c+it]==p} }
