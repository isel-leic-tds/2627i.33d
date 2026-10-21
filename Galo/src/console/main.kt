package console

import model.*

fun main() {
    var game: Game? = null
    while (true) {
        game?.show()
        print("> ")
        val cmd = readln().trim().uppercase().split(' ')
        when(val name = cmd[0]) {
            "NEW" -> game = game?.new() ?: Game()
            "EXIT" -> break
            "PLAY" -> {
                if (game!=null) {
                    val pos = cmd[1].toInt()
                    if ( game.canPlay(pos))
                        game = game.play(pos)
                }
            }
            else -> println("Invalid command $name")
        }
    }
}