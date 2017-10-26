@file:Suppress("unused")

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

    override fun configureElement() {
        super.configureElement()
        element.style.display = "flex"
        element.style.flexDirection = if (orientation == Horizontal) "row" else "column"
    }

    var justifyContent: JustifyContent = JustifyContent.Start
    set(value) {
        field = value
        element.style.justifyContent = value.cssName
    }

    var alignItems: AlignItems = AlignItems.Start
        set(value) {
            field = value
            element.style.alignItems = value.cssName
        }

    enum class Orientation {
        Horizontal,
        Vertical
    }
}

enum class AlignItems(
        val cssName: String
) {

    Start("flex-start"),
    End("flex-end"),
    Center("center"),
    Baseline("baseline"),
    Stretch("stretch")

}

enum class JustifyContent(
        val cssName: String) {
    Start("flex-start"),
    End("flex-end"),
    Center("center"),
    SpaceBetween("space-between"),
    SpaceAround("space-around"),
    SpaceEvenly("space-evenly")
}

