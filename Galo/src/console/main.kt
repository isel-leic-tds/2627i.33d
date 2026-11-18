package console

import model.*
//import storage.TextFileStorage
import storage.mongo.*


fun main() = MongoDriver("galo").use {
    var game: Game? = null
    //val storage: GameStorage = TextFileStorage("games", GameSerializer)
    val storage: GameStorage = MongoStorage("games", it, GameSerializer)
    val cmds: Map<String, Command> = getCommands(storage)
    while (true) {
        val (name, args) = readCommand()
        val cmd = cmds[name]
        if (cmd == null) println("Invalid command $name")
        else try {
            game = cmd.execute(args, game)
            if (cmd.isTerminate) break
            game?.show()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            println("Use: $name ${cmd.syntax}")
        } catch (e: IllegalStateException) {
            println(e.message)
        }
    }
}
