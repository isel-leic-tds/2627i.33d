package model

const val BOARD_SIZE = 3
const val BOARD_DIM = BOARD_SIZE*BOARD_SIZE

val EMPTY = null

sealed class GameState
data class Run(val turn: Player): GameState()
data class Win(val winner: Player): GameState()
data object Draw: GameState()

typealias Board = Map<Position, Player>
fun emptyBoard(): Board = emptyMap()

typealias Score = Map<Player?,Int>  // null -> Draws
fun startScore() = (Player.entries + null).associateWith { 0 }
fun Score.advance(player: Player?) = plus(player to getValue(player)+1)

data class Game(
    val first: Player = Player.X,
    val state: GameState = Run(first),
    val board: Board = emptyBoard(),
    val score: Score = startScore()
)

fun Game.new() = Game(
    first= first.other,
    score= when(state) {
        is Win, is Draw -> score
        is Run ->  score.advance(state.turn.other)
    }
)

fun Game.canPlay(p: Position) = board[p]==EMPTY

fun Game.play(pos: Position): Game = when(state) {
    is Run -> {
        check(canPlay(pos)) { "$pos already used" }
        val board = board + (pos to state.turn)
        val newState = when {
            board.isWinAt(pos) -> Win(state.turn)
            board.size == BOARD_DIM -> Draw
            else -> Run(state.turn.other)
        }
        Game(
            first = first,
            state = newState,
            board = board,
            score = when(newState) {
                is Win -> score.advance(state.turn)
                is Draw -> score.advance(null)
                is Run -> score
            }
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
