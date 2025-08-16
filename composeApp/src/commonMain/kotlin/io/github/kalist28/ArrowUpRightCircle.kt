package io.github.kalist28

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val ArrowUpRightCircle: ImageVector
    get() {
        if (_arrowUpRightCircle != null) {
            return _arrowUpRightCircle!!
        }
        _arrowUpRightCircle =
            Builder(
                    name = "ArrowUpRightCircle",
                    defaultWidth = 16.0.dp,
                    defaultHeight = 17.0.dp,
                    viewportWidth = 16.0f,
                    viewportHeight = 17.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFFFFFFFF)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(7.992f, 16.493f)
                        curveTo(12.264f, 16.493f, 15.79f, 12.968f, 15.79f, 8.704f)
                        curveTo(15.79f, 4.44f, 12.256f, 0.914f, 7.985f, 0.914f)
                        curveTo(3.721f, 0.914f, 0.203f, 4.44f, 0.203f, 8.704f)
                        curveTo(0.203f, 12.968f, 3.729f, 16.493f, 7.992f, 16.493f)
                        close()
                        moveTo(7.992f, 14.941f)
                        curveTo(4.535f, 14.941f, 1.77f, 12.162f, 1.77f, 8.704f)
                        curveTo(1.77f, 5.246f, 4.535f, 2.473f, 7.985f, 2.473f)
                        curveTo(11.443f, 2.473f, 14.223f, 5.246f, 14.23f, 8.704f)
                        curveTo(14.238f, 12.162f, 11.45f, 14.941f, 7.992f, 14.941f)
                        close()
                        moveTo(10.335f, 10.685f)
                        curveTo(10.72f, 10.685f, 10.961f, 10.399f, 10.961f, 9.992f)
                        verticalLineTo(6.489f)
                        curveTo(10.961f, 5.969f, 10.667f, 5.728f, 10.192f, 5.728f)
                        horizontalLineTo(6.667f)
                        curveTo(6.26f, 5.728f, 5.996f, 5.977f, 5.996f, 6.353f)
                        curveTo(5.996f, 6.73f, 6.26f, 6.979f, 6.674f, 6.979f)
                        horizontalLineTo(7.947f)
                        lineTo(8.964f, 6.858f)
                        lineTo(7.857f, 7.867f)
                        lineTo(5.22f, 10.504f)
                        curveTo(5.092f, 10.632f, 5.009f, 10.813f, 5.009f, 10.994f)
                        curveTo(5.009f, 11.393f, 5.288f, 11.657f, 5.68f, 11.657f)
                        curveTo(5.883f, 11.657f, 6.049f, 11.589f, 6.192f, 11.446f)
                        lineTo(8.814f, 8.824f)
                        lineTo(9.823f, 7.724f)
                        lineTo(9.71f, 8.802f)
                        verticalLineTo(9.999f)
                        curveTo(9.71f, 10.414f, 9.959f, 10.685f, 10.335f, 10.685f)
                        close()
                    }
                }
                .build()
        return _arrowUpRightCircle!!
    }

private var _arrowUpRightCircle: ImageVector? = null
