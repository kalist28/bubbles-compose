package io.github.kalist28.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.composables.core.SheetDetent
import com.composables.core.rememberBottomSheetState
import io.github.kalist28.bubbles.BubblesBottomSheet
import io.github.kalist28.bubbles.BubblesButton
import io.github.kalist28.bubbles.BubblesButtonDefaults
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.core.theme.BubblesTheme
import kotlinx.coroutines.launch

@Composable
fun BottomSheetScreen(paddingValues: PaddingValues) {
    val sheetState = rememberBottomSheetState(initialDetent = SheetDetent.Hidden)
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(BubblesTheme.colorScheme.systemBackground)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    BubblesText("Bottom Sheet Examples", style = BubblesTheme.typography.title3)
                    BubblesButton(
                        onClick = { scope.launch { sheetState.animateTo(SheetDetent.FullyExpanded) } },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BubblesText("Open Bottom Sheet")
                    }
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    BubblesText("Description", style = BubblesTheme.typography.callout)
                    BubblesText(
                        "Tap the button above to open a bottom sheet. " +
                                "Tap the dimmed area to close it.",
                        style = BubblesTheme.typography.subhead
                    )
                }
            }
        }




        BubblesBottomSheet(
            state = sheetState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BubblesText(
                    "Sheet Content",
                    style = BubblesTheme.typography.title3,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                BubblesText(
                    "This is a styled bottom sheet component following iOS design patterns.",
                    style = BubblesTheme.typography.body
                )

                BubblesButton(
                    onClick = { scope.launch { sheetState.animateTo(SheetDetent.Hidden) } },
                    modifier = Modifier.fillMaxWidth(),
                    colors = BubblesButtonDefaults.outlinedButtonColors()
                ) {
                    BubblesText("Close")
                }
            }
        }
    }
}
