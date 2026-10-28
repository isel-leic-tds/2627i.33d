package model

const val BOARD_SIZE = 3
const val BOARD_DIM = BOARD_SIZE*BOARD_SIZE

val EMPTY = null

data class Game(
    val first: Player = Player.X,
    val turn: Player = first,
    val board: List<Player?> = List(BOARD_DIM){ EMPTY }
)

fun Game.new() = Game(first= first.other)

fun Game.canPlay(p: Position) = board[p.index]==EMPTY

fun Game.play(pos: Position): Game {
    check(canPlay(pos)) { "$pos already used"}
    return Game(
        first = first,
        turn = turn.other,
        board = board.mapIndexed { i, p ->
            if (i == pos.index) turn else p
        }
    )
}

fun Game.isWinner(p: Player): Boolean {
    //Position.all.filter{ board[it.index] == p}
    return (0..8 step 4).all { board[it] == p } ||
            (2..6 step 2).all { board[it] == p } ||
            (0..6 step 3).any { r -> (0..2).all { board[r + it] == p } } ||
            (0..2).any { c -> (0..6 step 3).all { board[c + it] == p } }
}
