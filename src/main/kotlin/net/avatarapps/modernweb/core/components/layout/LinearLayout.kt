package net.avatarapps.modernweb.core.components.layout

import net.avatarapps.modernweb.core.components.layout.LinearLayout.Orientation.horizontal

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
class LinearLayout(
        val orientation: Orientation = horizontal
) : Layout() {
    override fun render(): dynamic {
        children.forEach {
            it.element.style.display = if (orientation == horizontal) "inline-block" else "block"
            element.append(it.render())
        }
        return element
    }

    enum class Orientation {
        horizontal,
        vertical
    }
}

