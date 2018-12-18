@file:Suppress("unused")

package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.layout.LinearLayout.Orientation.Horizontal
import com.narbase.kunafa.core.css.*

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
        private val initialOrientation: Orientation? = Horizontal
) : Container(parent) {

    override fun configureElement() {
        super.configureElement()
        this.orientation = initialOrientation
        addRuleSet(linearLayoutClass)
    }

    var orientation: Orientation? = null
        set(value) {
            field = value
            if (orientation == Horizontal) {
                addRuleSet(horizontalLayoutClass)
                removeRuleSet(verticalLayoutClass)
            } else {
                addRuleSet(verticalLayoutClass)
                removeRuleSet(horizontalLayoutClass)
            }
        }

    enum class Orientation {
        Horizontal,
        Vertical
    }

    companion object {
        val linearLayoutClass = classRuleSet {
            alignItems = Alignment.Start
            display = "inline-flex"
        }
        val verticalLayoutClass = classRuleSet {
            flexDirection = "column"
        }
        val horizontalLayoutClass = classRuleSet {
            flexDirection = "row"
        }
    }
}

