package io.github.kalist28.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kalist28.bubbles.BubblesButton
import io.github.kalist28.bubbles.BubblesButtonDefaults
import io.github.kalist28.bubbles.BubblesButtonSize
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.core.theme.BubblesTheme

@Composable
fun ButtonsScreen(paddingValues: PaddingValues) {
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
                BubblesText("Button Variants", style = BubblesTheme.typography.title3)
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BubblesButton(onClick = { }) {
                        BubblesText("Filled")
                    }
                    BubblesButton(
                        onClick = { },
                        colors = BubblesButtonDefaults.outlinedButtonColors()
                    ) {
                        BubblesText("Outlined")
                    }
                    BubblesButton(
                        onClick = { },
                        colors = BubblesButtonDefaults.tintedButtonColors()
                    ) {
                        BubblesText("Tinted")
                    }
                    BubblesButton(
                        onClick = { },
                        colors = BubblesButtonDefaults.clearButtonColors()
                    ) {
                        BubblesText("Clear")
                    }
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Disabled States", style = BubblesTheme.typography.title3)
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BubblesButton(onClick = { }, enabled = false) {
                        BubblesText("Filled")
                    }
                    BubblesButton(
                        onClick = { },
                        enabled = false,
                        colors = BubblesButtonDefaults.outlinedButtonColors()
                    ) {
                        BubblesText("Outlined")
                    }
                    BubblesButton(
                        onClick = { },
                        enabled = false,
                        colors = BubblesButtonDefaults.tintedButtonColors()
                    ) {
                        BubblesText("Tinted")
                    }
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Button Sizes", style = BubblesTheme.typography.title3)
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BubblesButtonSize.entries.forEach { size ->
                        BubblesButton(
                            onClick = { },
                            size = size
                        ) {
                            BubblesText(size.name)
                        }
                    }
                }
            }
        }
    }
}
