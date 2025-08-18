/*
 * Copyright 2025 Bubbles Compose project and open source contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kalist28.bubbles.core


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle
import platform.UIKit.UINotificationFeedbackGenerator
import platform.UIKit.UINotificationFeedbackType
import platform.UIKit.UISelectionFeedbackGenerator

@Composable
actual fun rememberBubblesHapticFeedback(): HapticFeedback {
    val current = LocalHapticFeedback.current

    return remember(current) {
        UIKitHapticFeedback(current)
    }
}

internal class UIKitHapticFeedback(
    private val delegate: HapticFeedback,
) : HapticFeedback {
    private val notificationFeedbackGenerator by lazy {
        UINotificationFeedbackGenerator()
    }

    private val selectionFeedbackGenerator by lazy {
        UISelectionFeedbackGenerator()
    }

    override fun performHapticFeedback(
        hapticFeedbackType: HapticFeedbackType
    ) = when (hapticFeedbackType) {
        HapticFeedbackType.LongPress,
        HapticFeedbackType.TextHandleMove -> {
            delegate.performHapticFeedback(hapticFeedbackType)
        }

        BubblesHapticFeedback.SelectionChanged -> {
            selectionFeedbackGenerator.selectionChanged()
        }

        BubblesHapticFeedback.Warning,
        BubblesHapticFeedback.Success,
        BubblesHapticFeedback.Error -> {
            notificationFeedbackGenerator.notificationOccurred(
                NotificationFeedbackMapping[hapticFeedbackType]!!,
            )
        }

        BubblesHapticFeedback.ImpactLight,
        BubblesHapticFeedback.ImpactMedium,
        BubblesHapticFeedback.ImpactHeavy,
        BubblesHapticFeedback.ImpactRigid,
        BubblesHapticFeedback.ImpactSoft -> {
            UIImpactFeedbackGenerator(
                ImpactFeedbackMapping[hapticFeedbackType]!!,
            ).impactOccurred()
        }
        else -> Unit
    }
}

private val NotificationFeedbackMapping by lazy {
    mapOf(
        BubblesHapticFeedback.Success to UINotificationFeedbackType.UINotificationFeedbackTypeSuccess,
        BubblesHapticFeedback.Warning to UINotificationFeedbackType.UINotificationFeedbackTypeWarning,
        BubblesHapticFeedback.Error to UINotificationFeedbackType.UINotificationFeedbackTypeError,
    )
}

private val ImpactFeedbackMapping by lazy {
    mapOf(
        BubblesHapticFeedback.ImpactLight to UIImpactFeedbackStyle.UIImpactFeedbackStyleLight,
        BubblesHapticFeedback.ImpactMedium to UIImpactFeedbackStyle.UIImpactFeedbackStyleMedium,
        BubblesHapticFeedback.ImpactHeavy to UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy,
        BubblesHapticFeedback.ImpactRigid to UIImpactFeedbackStyle.UIImpactFeedbackStyleRigid,
        BubblesHapticFeedback.ImpactSoft to UIImpactFeedbackStyle.UIImpactFeedbackStyleSoft,
    )
}