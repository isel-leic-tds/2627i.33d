package model

const val BOARD_SIZE = 3
const val BOARD_DIM = BOARD_SIZE*BOARD_SIZE

val EMPTY = null

sealed class GameState
class Run(val turn: Player): GameState()
class Win(val winner: Player): GameState()
object Draw: GameState()

typealias Board = Map<Position, Player>
fun emptyBoard(): Board = emptyMap()

data class Game(
    val first: Player = Player.X,
    val state: GameState = Run(first),
    val board: Board = emptyBoard()
)

fun Game.new() = Game(first= first.other)

fun Game.canPlay(p: Position) = board[p]==EMPTY

fun Game.play(pos: Position): Game = when(state) {
    is Run -> {
        check(canPlay(pos)) { "$pos already used" }
        val board = board + (pos to state.turn)
        Game(
            first = first,
            state = when {
                board.isWinAt(pos) -> Win(state.turn)
                board.size == BOARD_DIM -> Draw
                else -> Run(state.turn.other)
            },
            board = board
        )
    }
    is Draw, is Win -> error("Game over")
}

fun Board.isWinAt(pos: Position): Boolean {
    val player = getValue(pos)
    val places = filterValues { it==player }.keys
    if (places.size < BOARD_SIZE) return false
    val counts = [
        places.count { it.col == pos.col },
        places.count { it.row == pos.row },
        places.count { it.backSlash },
        places.count { it.slash }
    ]
    return counts.any{ it == BOARD_SIZE }
}
