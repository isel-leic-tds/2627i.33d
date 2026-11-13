package console

import model.*
import storage.Storage

data class Command(
    val syntax: String = "",
    val isTerminate: Boolean = false,
    val execute: (List<String>, Game?) -> Game? = { _, g -> g }
)

fun getCommands(gs: Storage<String,Game>): Map<String, Command> = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to Command { _, game -> game?.new() ?: Game() },
    "PLAY" to Command("<position>") { args, game ->
        val pos = requireNotNull(args.firstOrNull()){ "Missing position" }
        val index = requireNotNull(pos.toIntOrNull()){ "Invalid position $pos"}
        checkNotNull(game){ "Game not started" }.play(Position(index))
    },
    "SCORE" to Command( execute = { _, game -> game?.also{ it.score.show() }}),
    "SAVE" to storageCommand { file, game ->
        game.also{gs.create(file,checkNotNull(it){ "Game not started" })}
    },
    "LOAD" to storageCommand { file, _ -> gs.read(file) }
)

fun storageCommand(action: (String,Game?) -> Game?) = Command("<name>") { args, g ->
    val file = requireNotNull(args.firstOrNull()){ "Missing file name" }
    action(file,g)
}
