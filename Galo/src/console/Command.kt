package console

import model.*

// FP Style
data class Command(
    val syntax: String = "",
    val isTerminate: Boolean = false,
    val execute: (List<String>, Game?) -> Game? = { _, g -> g }
)

fun getCommands(): Map<String, Command> = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to Command { _, game -> game?.new() ?: Game() },
    "PLAY" to Command("<position>") { args, game ->
        val pos = requireNotNull(args.firstOrNull()){ "Missing position" }
        val index = requireNotNull(pos.toIntOrNull()){ "Invalid position $pos"}
        checkNotNull(game){ "Game not started" }.play(Position(index))
    },
    "SCORE" to Command( execute = { _, game -> game?.also{ it.score.show() }})
)

/* POO Style
open class Command(
    val syntax: String = "",
    val isTerminate: Boolean = false
) {
    open fun execute(args: List<String>, game: Game?): Game? = game
}

object Play : Command(syntax = "<position>") {
    override fun execute(args: List<String>, game: Game?): Game? {
        val pos = requireNotNull(args.firstOrNull()){ "Missing position" }
        val index = requireNotNull(pos.toIntOrNull()){ "Invalid position $pos"}
        return checkNotNull(game){ "Game not started" }.play(Position(index))
    }
}

fun getCommands(): Map<String, Command> = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to object: Command(){
        override fun execute(args: List<String>, game: Game?): Game =
            game?.new() ?: Game()
    },
    "PLAY" to Play,
    "SCORE" to object: Command() {
        override fun execute(args: List<String>, game: Game?): Game? =
            game?.also{ it.score.show() }
    }
)
*/
