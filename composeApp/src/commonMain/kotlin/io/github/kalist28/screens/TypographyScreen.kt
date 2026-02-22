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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kalist28.ArrowUpRightCircle
import io.github.kalist28.bubbles.BubblesHorizontalDivider
import io.github.kalist28.bubbles.BubblesIcon
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.core.theme.BubblesTheme

@Composable
fun TypographyScreen(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(BubblesTheme.colorScheme.systemBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            BubblesText("Display Large", style = BubblesTheme.typography.largeTitle)
        }

        item {
            BubblesText("Display Medium", style = BubblesTheme.typography.title1)
        }

        item {
            BubblesText("Display Small", style = BubblesTheme.typography.title2)
        }

        item {
            BubblesHorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp))
        }

        item {
            BubblesText("Headline Large", style = BubblesTheme.typography.largeTitle)
        }

        item {
            BubblesText("Headline Medium", style = BubblesTheme.typography.title1)
        }

        item {
            BubblesText("Headline Small", style = BubblesTheme.typography.title3)
        }

        item {
            BubblesHorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp))
        }

        item {
            BubblesText("Title Large", style = BubblesTheme.typography.title1)
        }

        item {
            BubblesText("Title Medium", style = BubblesTheme.typography.title2)
        }

        item {
            BubblesText("Title Small", style = BubblesTheme.typography.title3)
        }

        item {
            BubblesHorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp))
        }

        item {
            BubblesText("Body Large", style = BubblesTheme.typography.body)
        }

        item {
            BubblesText("Body Medium", style = BubblesTheme.typography.callout)
        }

        item {
            BubblesText("Body Small", style = BubblesTheme.typography.subhead)
        }

        item {
            BubblesHorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp))
        }

        item {
            BubblesText("Label Large", style = BubblesTheme.typography.callout)
        }

        item {
            BubblesText("Label Medium", style = BubblesTheme.typography.footnote)
        }

        item {
            BubblesText("Label Small", style = BubblesTheme.typography.caption1)
        }

        item {
            BubblesHorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp))
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BubblesText("Icons", style = BubblesTheme.typography.title3)
                BubblesIcon(
                    imageVector = ArrowUpRightCircle,
                    contentDescription = "Arrow Up Right Circle"
                )
            }
        }
    }
}