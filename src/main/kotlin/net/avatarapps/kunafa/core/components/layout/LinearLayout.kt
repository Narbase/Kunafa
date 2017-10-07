package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout.Orientation.horizontal
import net.avatarapps.kunafa.core.components.layout.LinearLayout.Orientation.vertical
import net.avatarapps.kunafa.core.dimensions.DependentDimension
import net.avatarapps.kunafa.core.dimensions.IndependentDimension
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
                    val pixels = 0.px
                    children.forEach {
                        pixels.pixels += (it.width.takeUnless { (it as? DependentDimension)?.dependency == DependentDimension.Dependency.parent} ?.pixels) ?: 0
                    }
                    pixels
                }
                vertical -> {
                    val pixels = 0.px
                    children.forEach {
                        if (it.width.pixels > pixels.pixels)
                            pixels.pixels = it.width.pixels
                    }
                    pixels
                }
            }
        }

    override val wrappedContentHeight: IndependentDimension
        get() {
            return when (orientation){
                horizontal -> {
                    val pixels = 0.px
                    children.forEach {
                        if (it.width.pixels > pixels.pixels)
                            pixels.pixels = it.width.pixels
                    }
                    pixels
                }

                vertical -> {
                    val pixels = 0.px
                    children.forEach {
                        pixels.pixels += it.height.pixels
                    }
                    pixels

                }
            }
        }

    enum class Orientation {
        horizontal,
        vertical
    }
}

