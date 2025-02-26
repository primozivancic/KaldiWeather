package software.ivancic.geo.ui.components.positionproviders

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

/**
 * Interfaces for positioning a popup within a window.
 */
@Stable
internal object PredictionPosition {
    /**
     * An interface to calculate the vertical position of a menu with respect to its anchor and
     * window. The returned y-coordinate is relative to the window.
     */
    @Stable
    fun interface Vertical {
        fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuHeight: Int,
        ): Int
    }

    /**
     * An interface to calculate the horizontal position of a menu with respect to its anchor,
     * window, and layout direction. The returned x-coordinate is relative to the window.
     */
    @Stable
    fun interface Horizontal {
        fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuWidth: Int,
            layoutDirection: LayoutDirection,
        ): Int
    }

    /**
     * Returns a [PredictionPosition.Horizontal] which aligns the start of the menu to the start of the
     * anchor.
     */
    fun startToAnchorStart(): Horizontal =
        AnchorAlignmentOffsetPosition.Horizontal(
            menuAlignment = Alignment.Start,
            anchorAlignment = Alignment.Start,
        )

    /**
     * Returns a [PredictionPosition.Vertical] which aligns the top of the menu to the bottom of the
     * anchor.
     */
    fun topToAnchorBottom(): Vertical =
        AnchorAlignmentOffsetPosition.Vertical(
            menuAlignment = Alignment.Top,
            anchorAlignment = Alignment.Bottom,
        )

    /**
     * Returns a [PredictionPosition.Vertical] which aligns the bottom of the menu to the top of the
     * anchor.
     */
    fun bottomToAnchorTop(): Vertical =
        AnchorAlignmentOffsetPosition.Vertical(
            menuAlignment = Alignment.Bottom,
            anchorAlignment = Alignment.Top,
        )
}

@Immutable
internal object AnchorAlignmentOffsetPosition {
    /**
     * A [PredictionPosition.Horizontal] which horizontally aligns the given [menuAlignment] with the
     * given [anchorAlignment].
     */
    @Immutable
    data class Horizontal(
        private val menuAlignment: Alignment.Horizontal,
        private val anchorAlignment: Alignment.Horizontal,
    ) : PredictionPosition.Horizontal {
        override fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuWidth: Int,
            layoutDirection: LayoutDirection,
        ): Int {
            val anchorAlignmentOffset = anchorAlignment.align(
                size = 0,
                space = anchorBounds.width,
                layoutDirection = layoutDirection,
            )
            val menuAlignmentOffset = -menuAlignment.align(
                size = 0,
                space = menuWidth,
                layoutDirection,
            )
            return anchorBounds.left + anchorAlignmentOffset + menuAlignmentOffset
        }
    }

    @Immutable
    data class Vertical(
        private val menuAlignment: Alignment.Vertical,
        private val anchorAlignment: Alignment.Vertical,
    ) : PredictionPosition.Vertical {
        override fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuHeight: Int,
        ): Int {
            val anchorAlignmentOffset = anchorAlignment.align(
                size = 0,
                space = anchorBounds.height,
            )
            val menuAlignmentOffset = -menuAlignment.align(
                size = 0,
                space = menuHeight,
            )
            return anchorBounds.top + anchorAlignmentOffset + menuAlignmentOffset
        }
    }
}

@Immutable
internal data class PredictionsPositionProvider(
    val minHeight: Int,
    val onPositionCalculated: (popupMaxHeight: Int) -> Unit = { },
) : PopupPositionProvider {
    // Horizontal position
    private val startToAnchorStart: PredictionPosition.Horizontal =
        PredictionPosition.startToAnchorStart()

    // Vertical position
    private val topToAnchorBottom: PredictionPosition.Vertical =
        PredictionPosition.topToAnchorBottom()
    private val bottomToAnchorTop: PredictionPosition.Vertical =
        PredictionPosition.bottomToAnchorTop()

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {
        val x = startToAnchorStart.position(
            anchorBounds = anchorBounds,
            windowSize = windowSize,
            menuWidth = popupContentSize.width,
            layoutDirection = layoutDirection
        )

        val (y, maxHeight) = if (minHeight < windowSize.height - anchorBounds.bottom || minHeight >= anchorBounds.top) {
            topToAnchorBottom.position(
                anchorBounds = anchorBounds,
                windowSize = windowSize,
                menuHeight = popupContentSize.height
            ) to windowSize.height - anchorBounds.bottom
        } else {
            bottomToAnchorTop.position(
                anchorBounds = anchorBounds,
                windowSize = windowSize,
                menuHeight = minHeight.coerceAtMost(popupContentSize.height)
            ) to minHeight.coerceAtMost(popupContentSize.height)
        }

        val menuOffset = IntOffset(x, y)
        onPositionCalculated(maxHeight)
        return menuOffset
    }
}
