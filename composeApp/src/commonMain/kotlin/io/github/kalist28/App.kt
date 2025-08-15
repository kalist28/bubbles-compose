package io.github.kalist28

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kalist28.bubbles.BubblesButton
import io.github.kalist28.bubbles.BubblesButtonDefaults
import io.github.kalist28.bubbles.BubblesButtonSize
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.BubblesTheme
import io.github.kalist28.bubbles.core.theme.BubblesTheme
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
            Buttons()
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