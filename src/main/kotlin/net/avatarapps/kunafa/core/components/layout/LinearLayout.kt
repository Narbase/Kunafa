package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout.Orientation.Horizontal
import net.avatarapps.kunafa.core.components.layout.LinearLayout.Orientation.Vertical
import net.avatarapps.kunafa.core.dimensions.IndependentDimension
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.dimensions.plus

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
        val orientation: Orientation = Horizontal
) : Container(parent) {

    override fun add(child: View) {
        child.element.style.display = if (orientation == Horizontal) "inline-block" else "block"
        super.add(child)
    }

    override val wrappedContentWidth: IndependentDimension
        get() {
            return when (orientation) {
                Horizontal -> {
                    var pixels = 0.px
                    children.forEach {
                        pixels += (it.extendedWidth ?: 0.px)
                    }
                    pixels += paddingStart + paddingEnd
                    pixels
                }
                Vertical -> {
                    var pixels = 0.px
                    children.forEach {
                        println("$id ew: ${it.extendedWidth}")
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
            return when (orientation) {
                Horizontal -> {
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
                Vertical -> {
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
        Horizontal,
        Vertical
    }
}

