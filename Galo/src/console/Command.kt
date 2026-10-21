package console

import model.Game

class Command {
    val syntaxe: String = TODO()
    val isTerminate: Boolean = TODO()
    fun execute(args: List<String>, game: Game?): Game? = TODO()
}

fun getCommands(): Map<String, Command> = TODO()
