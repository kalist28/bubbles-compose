package io.github.kalist28

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Bubblescompose",
    ) {
        App()
    }
}