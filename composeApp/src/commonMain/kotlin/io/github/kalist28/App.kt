package io.github.kalist28

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.kalist28.bubbles.BubblesButton
import io.github.kalist28.bubbles.BubblesButtonDefaults
import io.github.kalist28.bubbles.BubblesButtonSize
import io.github.kalist28.bubbles.BubblesScaffold
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.BubblesTheme as BubblesThemeProvider
import io.github.kalist28.bubbles.BubblesTopAppBar
import io.github.kalist28.bubbles.core.theme.BubblesTheme
import io.github.kalist28.screens.BottomSheetScreen
import io.github.kalist28.screens.ButtonsScreen
import io.github.kalist28.screens.InputsScreen
import io.github.kalist28.screens.MainScreen
import io.github.kalist28.screens.NavigationScreen
import io.github.kalist28.screens.SelectionScreen
import io.github.kalist28.screens.TypographyScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

sealed class Screen {
    object Main : Screen()
    object Buttons : Screen()
    object Inputs : Screen()
    object Selection : Screen()
    object Typography : Screen()
    object NavigationComponents : Screen()
    object BottomSheet : Screen()
}

@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Main) }
    var isDarkTheme by remember { mutableStateOf(false) }

    BubblesThemeProvider {
        BubblesScaffold(
            topBar = {
                BubblesTopAppBar(
                    title = {
                        BubblesText(
                            when (currentScreen) {
                                Screen.Main -> "Bubbles Components"
                                Screen.Buttons -> "Buttons"
                                Screen.Inputs -> "Text Input & Slider"
                                Screen.Selection -> "Selection Controls"
                                Screen.Typography -> "Typography & Icons"
                                Screen.NavigationComponents -> "Navigation"
                                Screen.BottomSheet -> "Bottom Sheet"
                            }
                        )
                    },
                    actions = {
                        if (currentScreen != Screen.Main) {
                            BubblesButton(
                                onClick = { currentScreen = Screen.Main },
                                size = BubblesButtonSize.Small,
                                colors = BubblesButtonDefaults.clearButtonColors()
                            ) {
                                BubblesText("← Back")
                            }
                        } else {
                            BubblesButton(
                                onClick = { isDarkTheme = !isDarkTheme },
                                size = BubblesButtonSize.Small,
                                colors = BubblesButtonDefaults.clearButtonColors()
                            ) {
                                BubblesText(if (isDarkTheme) "☀️" else "🌙")
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            when (currentScreen) {
                Screen.Main -> MainScreen(paddingValues) { screen -> currentScreen = screen }
                Screen.Buttons -> ButtonsScreen(paddingValues)
                Screen.Inputs -> InputsScreen(paddingValues)
                Screen.Selection -> SelectionScreen(paddingValues)
                Screen.Typography -> TypographyScreen(paddingValues)
                Screen.NavigationComponents -> NavigationScreen(paddingValues)
                Screen.BottomSheet -> BottomSheetScreen(paddingValues)
            }
        }
    }
}