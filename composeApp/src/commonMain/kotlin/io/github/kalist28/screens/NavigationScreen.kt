package io.github.kalist28.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kalist28.bubbles.BubblesSegmentedControl
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.core.theme.BubblesTheme
import io.github.kalist28.bubbles.ControlTab

@Composable
fun NavigationScreen(paddingValues: PaddingValues) {
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
                BubblesText("Segmented Control", style = BubblesTheme.typography.title3)
                var selectedTab by remember { mutableStateOf(0) }
                BubblesSegmentedControl(selectedTab) {
                    repeat(4) { i ->
                        ControlTab(
                            onClick = { selectedTab = i },
                            isSelected = i == selectedTab,
                            isEnabled = true
                        ) {
                            BubblesText("Tab $i")
                        }
                    }
                }
                BubblesText("Selected: Tab $selectedTab", style = BubblesTheme.typography.callout)
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Disabled Segmented Control", style = BubblesTheme.typography.title3)
                var selected by remember { mutableStateOf(1) }
                BubblesSegmentedControl(selected) {
                    repeat(4) { i ->
                        ControlTab(
                            onClick = { selected = i },
                            isSelected = i == selected,
                            isEnabled = i % 2 == 0
                        ) {
                            BubblesText("Tab $i")
                        }
                    }
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Many Tabs", style = BubblesTheme.typography.title3)
                var selectedTab by remember { mutableStateOf(0) }
                BubblesSegmentedControl(selectedTab) {
                    repeat(6) { i ->
                        ControlTab(
                            onClick = { selectedTab = i },
                            isSelected = i == selectedTab,
                            isEnabled = true
                        ) {
                            BubblesText("${i + 1}")
                        }
                    }
                }
            }
        }
    }
}
