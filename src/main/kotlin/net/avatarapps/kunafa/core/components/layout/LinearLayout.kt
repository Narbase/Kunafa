package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout.Orientation.horizontal
import net.avatarapps.kunafa.core.components.layout.LinearLayout.Orientation.vertical
import net.avatarapps.kunafa.core.dimensions.IndependentDimension
import net.avatarapps.kunafa.core.dimensions.plus
import net.avatarapps.kunafa.core.dimensions.px

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
class LinearLayout(
        parent: Container,
        val orientation: Orientation = horizontal
) : Container(parent) {

    override fun add(child: View) {
        child.element.style.display = if (orientation == horizontal) "inline-block" else "block"
        super.add(child)
    }

    override val wrappedContentWidth: IndependentDimension
        get() {
            return when (orientation) {
                horizontal -> {
                    var pixels = 0.px
                    children.forEach {
                        pixels += (it.extendedWidth ?: 0.px)
                    }
                    pixels += paddingStart + paddingEnd
                    pixels
                }
                vertical -> {
                    var pixels = 0.px
                    children.forEach {
                        it.extendedWidth?.let {
                            if (it.pixels > pixels.pixels)
                                pixels = it
                        }
                    }
                    pixels += paddingStart + paddingEnd
                    pixels
                }
            }
        }

    override val wrappedContentHeight: IndependentDimension
        get() {
            return when (orientation){
                horizontal -> {
                    var pixels = 0.px
                    children.forEach {
                        it.extendedHeight?.let {
                            if (it.pixels > pixels.pixels)
                                pixels = it
                        }
                    }
                    pixels += paddingTop + paddingBottom
                    pixels
                }

                vertical -> {
                    var pixels = 0.px
                    children.forEach {
                        pixels += (it.extendedHeight ?: 0.px)
                    }
                    pixels += paddingTop + paddingBottom
                    pixels

                }
            }
        }

    enum class Orientation {
        horizontal,
        vertical
    }
}

