package gui

import androidx.compose.material3.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
    application(
        exitProcessOnExit = false
    ) {
        Window(onCloseRequest = ::exitApplication) {
            Button(onClick = { println("Click")}) {
                Text("Hello")
            }
        }
    }
    println("Bye")
}