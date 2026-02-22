package io.github.kalist28.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kalist28.ArrowUpRightCircle
import io.github.kalist28.bubbles.BubblesBorderedTextField
import io.github.kalist28.bubbles.BubblesSlider
import io.github.kalist28.bubbles.BubblesSliderDefaults
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.BubblesTextField
import io.github.kalist28.bubbles.BubblesTextFieldDefaults
import io.github.kalist28.bubbles.core.LocalContentColor
import io.github.kalist28.bubbles.core.theme.BubblesTheme

@Composable
fun InputsScreen(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(BubblesTheme.colorScheme.systemBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Text Field", style = BubblesTheme.typography.title3)
                var text by remember { mutableStateOf("Test text") }
                BubblesTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { BubblesText("Enter text") }
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Bordered Text Field", style = BubblesTheme.typography.title3)
                var text by remember { mutableStateOf("") }
                BubblesBorderedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { BubblesText("Enter text") }
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Text Field with Icons", style = BubblesTheme.typography.title3)
                var text by remember { mutableStateOf("") }
                BubblesBorderedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { BubblesText("Search") },
                    leadingIcon = {
                        Icon(
                            imageVector = ArrowUpRightCircle,
                            contentDescription = "Search",
                            tint = LocalContentColor.current
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = ArrowUpRightCircle,
                            contentDescription = "Clear",
                            tint = LocalContentColor.current
                        )
                    }
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Disabled Text Field", style = BubblesTheme.typography.title3)
                BubblesBorderedTextField(
                    value = "Disabled",
                    onValueChange = { },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    placeholder = { BubblesText("Placeholder") }
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Error State", style = BubblesTheme.typography.title3)
                var text by remember { mutableStateOf("") }
                BubblesBorderedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    isError = text.isEmpty(),
                    placeholder = { BubblesText("Required field") }
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Slider", style = BubblesTheme.typography.title3)
                var sliderValue by remember { mutableStateOf(0.5f) }
                BubblesSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    modifier = Modifier.fillMaxWidth(),
                    steps = 20
                )
                BubblesText("Value: ${(sliderValue * 100).toInt()}%")
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Disabled Slider", style = BubblesTheme.typography.title3)
                BubblesSlider(
                    value = 0.5f,
                    onValueChange = { },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
            }
        }
    }
}