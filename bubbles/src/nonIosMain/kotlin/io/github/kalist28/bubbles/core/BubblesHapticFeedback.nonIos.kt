package io.github.kalist28.bubbles.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.platform.LocalHapticFeedback

@Composable
actual fun rememberBubblesHapticFeedback(): HapticFeedback =
    LocalHapticFeedback.current