package gui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App2()
    }
}

@Composable
fun App2() {
    print("\nApp ")
    var text by remember { mutableStateOf("Hello, world!") }
    //print("text=${text}")
    MaterialTheme {
        Button(onClick = {
            println("Click ")
            text = "Hello TDS"
        }) {
            print("Button text=${text}")
            Text(text)
        }
    }
}