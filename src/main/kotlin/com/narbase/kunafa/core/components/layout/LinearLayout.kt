@file:Suppress("unused")

package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.layout.LinearLayout.Orientation.Horizontal
import com.narbase.kunafa.core.components.layout.LinearLayout.Orientation.Vertical

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
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
        element.style.display = "inline-flex"
        element.style.flexDirection = if (orientation == Horizontal) "row" else "column"
        alignItems = Alignment.Start
    }


    var justifyContent: JustifyContent = JustifyContent.Start
    set(value) {
        field = value
        element.style.justifyContent = value.cssName
    }

    var alignItems: Alignment = Alignment.Start
        set(value) {
            field = value
            element.style.alignItems = value.cssName
        }

    enum class Orientation {
        Horizontal,
        Vertical
    }
}

enum class Alignment(
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

