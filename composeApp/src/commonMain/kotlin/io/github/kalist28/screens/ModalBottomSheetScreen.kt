package io.github.kalist28.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.core.rememberModalBottomSheetState
import io.github.kalist28.bubbles.BubblesModalBottomSheet
import io.github.kalist28.bubbles.BubblesButton
import io.github.kalist28.bubbles.BubblesButtonDefaults
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.core.theme.BubblesTheme
import kotlinx.coroutines.launch

@Composable
fun ModalBottomSheetScreen(paddingValues: PaddingValues) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(BubblesTheme.colorScheme.systemBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Modal Bottom Sheet Examples", style = BubblesTheme.typography.title3)
                BubblesButton(
                    onClick = { scope.launch { sheetState.show() } },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BubblesText("Open Modal Bottom Sheet")
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Description", style = BubblesTheme.typography.callout)
                BubblesText(
                    "Modal bottom sheet focuses user attention with a dimmed background. " +
                        "Tap outside or the close button to dismiss.",
                    style = BubblesTheme.typography.subhead
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Use Cases", style = BubblesTheme.typography.callout)
                BubblesText(
                    "• Confirmation dialogs\n" +
                        "• Important user actions\n" +
                        "• Selection menus\n" +
                        "• Settings panels",
                    style = BubblesTheme.typography.subhead
                )
            }
        }
    }

    // Modal Bottom Sheet
    BubblesModalBottomSheet(
        state = sheetState,
        showDragIndication = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BubblesText(
                "Modal Sheet Content",
                style = BubblesTheme.typography.title3,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            BubblesText(
                "This is a modal bottom sheet variant that demands user attention. " +
                    "The background is dimmed and interaction with content behind is blocked.",
                style = BubblesTheme.typography.body
            )

            BubblesText(
                "Perfect for confirmations, selections, or other important interactions.",
                style = BubblesTheme.typography.subhead
            )

            BubblesButton(
                onClick = { scope.launch { sheetState.hide() } },
                modifier = Modifier.fillMaxWidth(),
                colors = BubblesButtonDefaults.outlinedButtonColors()
            ) {
                BubblesText("Close")
            }
        }
    }
}
