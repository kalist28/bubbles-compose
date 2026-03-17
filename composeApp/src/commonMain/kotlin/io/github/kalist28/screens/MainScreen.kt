package io.github.kalist28.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kalist28.Screen
import io.github.kalist28.bubbles.BubblesButton
import io.github.kalist28.bubbles.BubblesButtonDefaults
import io.github.kalist28.bubbles.BubblesHorizontalDivider
import io.github.kalist28.bubbles.BubblesText
import io.github.kalist28.bubbles.core.theme.BubblesTheme

data class CategoryItem(
    val title: String,
    val description: String,
    val screen: Screen
)

@Composable
fun MainScreen(
    paddingValues: PaddingValues,
    onNavigate: (Screen) -> Unit
) {
    val categories = listOf(
        CategoryItem(
            "Buttons",
            "Filled, outlined, tinted, and clear button variants",
            Screen.Buttons
        ),
        CategoryItem(
            "Text Input & Slider",
            "Text fields, bordered fields, and sliders",
            Screen.Inputs
        ),
        CategoryItem(
            "Selection Controls",
            "Checkboxes, radio buttons, and switches",
            Screen.Selection
        ),
        CategoryItem(
            "Typography & Icons",
            "Text styles, icons, and separators",
            Screen.Typography
        ),
        CategoryItem(
            "Navigation",
            "Tab rows and segmented controls",
            Screen.NavigationComponents
        ),
        CategoryItem(
            "Bottom Sheet",
            "iOS-inspired bottom sheet with drag indication",
            Screen.BottomSheet
        ),
        CategoryItem(
            "Modal Bottom Sheet",
            "Modal variant with dimmed background and full attention focus",
            Screen.ModalBottomSheet
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(BubblesTheme.colorScheme.systemBackground)
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                BubblesButton(
                    onClick = { onNavigate(category.screen) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = BubblesButtonDefaults.clearButtonColors()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        BubblesText(
                            category.title,
                            style = BubblesTheme.typography.callout
                        )
                        BubblesText(
                            category.description,
                            style = BubblesTheme.typography.subhead
                        )
                    }
                }
            }
            if (index < categories.size - 1) {
                BubblesHorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}