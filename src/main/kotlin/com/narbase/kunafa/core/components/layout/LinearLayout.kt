@file:Suppress("unused")

package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.layout.LinearLayout.Orientation.Horizontal
import com.narbase.kunafa.core.css.Alignment
import com.narbase.kunafa.core.css.alignItems
import com.narbase.kunafa.core.css.classRuleSet

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
    }


//    var justifyContent: JustifyContent = JustifyContent.Start
//    set(value) {
//        field = value
//        element.style.justifyContent = value.name
//    }
//
//    var alignItems: Alignment = Alignment.Start
//        set(value) {
//            field = value
//            element.style.alignItems = value.name
//        }

    enum class Orientation {
        Horizontal,
        Vertical
    }

    companion object {
        val linearLayoutClass = classRuleSet {
            alignItems = Alignment.Start
        }
    }
}

