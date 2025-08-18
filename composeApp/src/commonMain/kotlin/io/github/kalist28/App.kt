package io.github.kalist28

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import io.github.kalist28.bubbles.BubblesBorderedTextField
import io.github.kalist28.bubbles.BubblesButton
import io.github.kalist28.bubbles.BubblesButtonDefaults
import io.github.kalist28.bubbles.BubblesButtonSize
import io.github.kalist28.bubbles.BubblesCheckBox
import io.github.kalist28.bubbles.BubblesRadioButton
import io.github.kalist28.bubbles.BubblesRadioButtonsColumn
import io.github.kalist28.bubbles.BubblesSegmentedControl
import io.github.kalist28.bubbles.ControlTab
import io.github.kalist28.bubbles.BubblesSlider
import io.github.kalist28.bubbles.BubblesSliderDefaults
import io.github.kalist28.bubbles.BubblesSwitch
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.BubblesTextField
import io.github.kalist28.bubbles.BubblesTextFieldDefaults
import io.github.kalist28.bubbles.BubblesTheme
import io.github.kalist28.bubbles.BubblesTriStateCheckBox
import io.github.kalist28.bubbles.core.LocalContentColor
import io.github.kalist28.bubbles.core.theme.BubblesColors
import io.github.kalist28.bubbles.core.theme.BubblesTheme
import io.github.kalist28.bubbles.core.theme.systemPaleBlue
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BubblesTheme {
        Column(
            modifier = Modifier
                .background(BubblesTheme.colorScheme.systemBackground)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var index by remember { mutableStateOf(0) }
            BubblesSegmentedControl(index) {
                repeat(5) { i ->
                    ControlTab({
                        index = i
                    }, isSelected = i == index, isEnabled = i % 2 == 0) {
                        BubblesText("Tab $i")
                    }
                }
            }


            var sliderValue by remember { mutableStateOf(0.5f) }
            BubblesSlider(
                sliderValue,
                { sliderValue = it },
                steps = 20,
                colors = BubblesSliderDefaults.colors()
            )
            BubblesSlider(1 - sliderValue, { sliderValue = it }, enabled = false)

            val radioOptions = listOf("Calls", "Missed", "Friends")
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
            BubblesRadioButtonsColumn(
                items = radioOptions,
                selectedPredicate = { it == selectedOption },
                onItemClick = onOptionSelected,
                itemContent = { modifier, item ->
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = modifier.padding(8.dp)
                    ) {
                        BubblesRadioButton(
                            selected = item == selectedOption,
                            onClick = null
                        )
                        BubblesText(text = item)
                    }
                }
            )


            Row {
                BubblesRadioButton(true, {})
                BubblesRadioButton(false, {})
                BubblesRadioButton(true, {}, enabled = false)
                BubblesRadioButton(false, {}, enabled = false)
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
            ) {

                BubblesSwitch(true, {})
                BubblesSwitch(false, {})
                BubblesSwitch(true, {}, enabled = false)
                BubblesSwitch(false, {}, enabled = false)
            }
            Row {
                var state by remember { mutableStateOf(ToggleableState.On) }
                BubblesTriStateCheckBox(
                    state = state,
                    onClick = {
                        state = ToggleableState
                            .entries[(state.ordinal + 1) % ToggleableState.entries.size]
                    }
                )

                BubblesCheckBox(
                    checked = true,
                    onCheckedChange = {}
                )

                BubblesCheckBox(
                    checked = false,
                    onCheckedChange = {}
                )

                BubblesCheckBox(
                    checked = true,
                    onCheckedChange = {},
                    enabled = false
                )

                BubblesCheckBox(
                    checked = false,
                    onCheckedChange = {},
                    enabled = false
                )
            }
            Buttons()

            var text1 by remember { mutableStateOf("Test text") }
            BubblesTextField(
                value = text1,
                onValueChange = { text1 = it },
                colors = BubblesTextFieldDefaults.colors(
                    focusedContainerColor = BubblesColors.systemPaleBlue
                ),
                placeholder = { BubblesText("Placeholder") },
            )

            BubblesBorderedTextField(
                value = text1,
                onValueChange = { text1 = it },
                colors = BubblesTextFieldDefaults.colors(
                    focusedContainerColor = BubblesColors.systemPaleBlue
                ),
                placeholder = { BubblesText("Placeholder") },
            )

            BubblesBorderedTextField(
                value = text1,
                onValueChange = { text1 = it },
                colors = BubblesTextFieldDefaults.colors(),
                enabled = false,
                placeholder = { BubblesText("Placeholder") },
            )

            BubblesBorderedTextField(
                value = text1,
                onValueChange = { text1 = it },
                isError = true,
                colors = BubblesTextFieldDefaults.colors(),
                placeholder = { BubblesText("Placeholder") },
                leadingIcon = {
                    Icon(
                        imageVector = ArrowUpRightCircle,
                        contentDescription = "",
                        tint = LocalContentColor.current
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = ArrowUpRightCircle,
                        contentDescription = "",
                        tint = LocalContentColor.current
                    )
                }
            )
        }
    }
}

@Composable
private fun Buttons() {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
    ) {
        BubblesButton(onClick = { }) {
            BubblesText("Filled button")
        }

        BubblesButton(
            onClick = { },
            colors = BubblesButtonDefaults.outlinedButtonColors(),
        ) {
            BubblesText("Outlined button")
        }

        BubblesButton(
            onClick = { },
            colors = BubblesButtonDefaults.clearButtonColors()
        ) {
            BubblesText("Plain button")
        }

        BubblesButton(
            onClick = { },
            colors = BubblesButtonDefaults.tintedButtonColors(),
        ) {
            BubblesText("Tinted button")
        }

        BubblesButton(onClick = { }, enabled = false) {
            BubblesText("Disabled button")
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BubblesButtonSize.entries.forEach { size ->
            BubblesButton(
                onClick = { },
                size = size,
            ) {
                BubblesText(size.name)
            }
        }
    }
}