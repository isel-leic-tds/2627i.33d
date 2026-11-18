package model

import storage.Storage

typealias GameStorage = Storage<Name,Game>

data class Clash(
    val gs: GameStorage,
    val name: Name,
    val side: Player,
    val game: Game,
)

fun startClash(s: GameStorage, name: Name): Clash =
    Clash(s, name, Player.X, Game()).also { s.create(name, it.game) }

fun joinClash(s: GameStorage, name: Name): Clash {
    val game = s.read(name) ?: error("No game with name $name")
    return Clash(s, name, Player.O, game)
}

fun Clash.refresh(): Clash {
    val game = gs.read(name) ?: error("No game with name $name")
    return copy(game = game)
}

fun Clash.play(pos: Position): Clash {
    check(game.state is Run && game.state.turn==side){"Not your turn"}
    val newGame = game.play(pos)
    gs.update(name, newGame)
    return copy(game = newGame)
}

fun Clash.newBoard() = copy(game = game.new().also { gs.update(name, it) })