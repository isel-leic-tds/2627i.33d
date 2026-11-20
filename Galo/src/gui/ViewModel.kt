package gui

import androidx.compose.runtime.*
import model.*
import storage.TextFileStorage
import storage.mongo.MongoDriver
import storage.mongo.MongoStorage

const val baseNameStorage = "games"

class TextFileViewModel:
    ViewModel(TextFileStorage(baseNameStorage, GameSerializer))

class MongoViewModel(
    val driver: MongoDriver = MongoDriver("galo"),
    gs: GameStorage = MongoStorage(baseNameStorage, driver, GameSerializer)
): ViewModel(gs) {
    override fun exit() { driver.close() }
}

enum class EditNameFor(val text: String) { START("Start"), JOIN("Join") }

open class ViewModel(gs: GameStorage) {
    var clash by mutableStateOf(Clash(gs))
    private set

    var editName by mutableStateOf<EditNameFor?>(null)
    private set

    fun start() { editName = EditNameFor.START }
    fun join() { editName = EditNameFor.JOIN }
    fun action(name: Name) {
        clash = when( editName ){
            EditNameFor.START -> clash.start(name)
            EditNameFor.JOIN -> clash.join(name)
            null -> error("Invalid state for edit")
        }
    }


    fun new() {
        if (!showScore) game = game.new()
    }
    fun play(pos: Position) {
        if (game.state is Run) {
            game = game.play(pos)
            if (game.state !is Run) showScore = true
        }
    }

    var showScore by mutableStateOf(false)
    private set

    fun showScore() { showScore = true }
    fun hideScore() { showScore = false }

    open fun exit() { }
}