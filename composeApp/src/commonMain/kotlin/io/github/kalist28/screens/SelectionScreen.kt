package io.github.kalist28.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import io.github.kalist28.bubbles.BubblesCheckBox
import io.github.kalist28.bubbles.BubblesRadioButton
import io.github.kalist28.bubbles.BubblesRadioButtonsColumn
import io.github.kalist28.bubbles.BubblesSwitch
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.BubblesTriStateCheckBox
import io.github.kalist28.bubbles.core.theme.BubblesTheme

@Composable
fun SelectionScreen(paddingValues: PaddingValues) {
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
                BubblesText("Checkboxes", style = BubblesTheme.typography.title3)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var checked1 by remember { mutableStateOf(true) }
                    var checked2 by remember { mutableStateOf(false) }
                    BubblesCheckBox(
                        checked = checked1,
                        onCheckedChange = { checked1 = it }
                    )
                    BubblesCheckBox(
                        checked = checked2,
                        onCheckedChange = { checked2 = it }
                    )
                    BubblesCheckBox(
                        checked = true,
                        onCheckedChange = { },
                        enabled = false
                    )
                    BubblesCheckBox(
                        checked = false,
                        onCheckedChange = { },
                        enabled = false
                    )
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Tri-State Checkbox", style = BubblesTheme.typography.title3)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var state by remember { mutableStateOf(ToggleableState.On) }
                    BubblesTriStateCheckBox(
                        state = state,
                        onClick = {
                            state = ToggleableState
                                .entries[(state.ordinal + 1) % ToggleableState.entries.size]
                        }
                    )
                    BubblesText("Click to cycle states")
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Radio Buttons", style = BubblesTheme.typography.title3)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BubblesRadioButton(true, {})
                    BubblesRadioButton(false, {})
                    BubblesRadioButton(true, {}, enabled = false)
                    BubblesRadioButton(false, {}, enabled = false)
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Radio Buttons Column", style = BubblesTheme.typography.title3)
                val radioOptions = listOf("Option 1", "Option 2", "Option 3")
                var selectedOption by remember { mutableStateOf(radioOptions[0]) }
                BubblesRadioButtonsColumn(
                    items = radioOptions,
                    selectedPredicate = { it == selectedOption },
                    onItemClick = { selectedOption = it },
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
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Switches", style = BubblesTheme.typography.title3)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var switch1 by remember { mutableStateOf(true) }
                    var switch2 by remember { mutableStateOf(false) }
                    BubblesSwitch(switch1, { switch1 = it })
                    BubblesSwitch(switch2, { switch2 = it })
                    BubblesSwitch(true, { }, enabled = false)
                    BubblesSwitch(false, { }, enabled = false)
                }
            }
        }
    }
}