package gui

import androidx.compose.runtime.*
import model.*

class ViewModel {
    var game by mutableStateOf(Game())
    private set

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
}