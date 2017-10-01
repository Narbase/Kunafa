package net.avatarapps.modernweb.core.components.layout

import net.avatarapps.modernweb.core.components.View
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
        parent: Layout,
        val orientation: Orientation = horizontal
) : Layout(parent) {

    override fun add(child: View) {
        child.element.style.display = if (orientation == horizontal) "inline-block" else "block"
        element.append(child.element)
        children.add(child)
    }

    enum class Orientation {
        horizontal,
        vertical
    }
}

