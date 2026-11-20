package model

import storage.Storage

typealias GameStorage = Storage<Name,Game>

open class Clash(val gs: GameStorage)

class ClashRun(
    gs: GameStorage,
    val name: Name,
    val side: Player,
    val game: Game,
) : Clash(gs)

fun Clash.deleteIfOwner() {
    if (this is ClashRun && side == Player.X) gs.delete(name)
}

fun Clash.start(name: Name) =
    ClashRun(gs, name, Player.X, Game()).also {
        gs.create(name, it.game)
        deleteIfOwner()
    }

fun Clash.join(name: Name): ClashRun {
    val game = gs.read(name) ?: error("No game with name $name")
    deleteIfOwner()
    return ClashRun(gs, name, Player.O, game)
}

fun Clash.run( oper: ClashRun.()-> Game ): ClashRun {
    check(this is ClashRun) { "Clash not started "}
    return ClashRun(gs, name, side, oper())
}

fun Clash.refresh() = run {
    gs.read(name) ?: error("No game with name $name")
}

fun Clash.play(pos: Position) = run {
    game.play(pos).also {
        check((game.state as Run).turn == side) { "Not your turn" }
        gs.update(name, it)
    }
}

val Clash.canNew: Boolean get() =
    this is ClashRun && side == when (game.state) {
        is Run -> game.state.turn
        else -> game.first.other
    }

fun Clash.newBoard() = run {
    check(canNew) { "new not allowed" }
    game.new().also { gs.update(name, it) }
}