package console

import model.*

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
    "PLAY" to Play
)
