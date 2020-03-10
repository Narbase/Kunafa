@file:Suppress("unused")

package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.layout.LinearLayout.Orientation.Horizontal
import com.narbase.kunafa.core.css.*

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
open class LinearLayout(
        parent: View?,
        private val initialOrientation: Orientation? = Horizontal
) : View(parent) {

    override fun configureElement() {
        super.configureElement()
        if (initialOrientation == Horizontal) {
            addRuleSet(horizontalLayoutClass)
        } else {
            addRuleSet(verticalLayoutClass)
        }
        addRuleSet(linearLayoutClass)
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

