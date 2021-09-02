package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.css.classRuleSet
import com.narbase.kunafa.core.css.isScrollableHorizontally
import com.narbase.kunafa.core.css.isScrollableVertically

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class ScrollView(
        parent: View?,
        private val initialOrientation: LinearLayoutOrientation?
) : LinearLayout(parent, initialOrientation) {

    override fun configureElement() {
        super.configureElement()
        if (initialOrientation == LinearLayoutOrientation.Horizontal) {
            addRuleSet(horizontalScrollLayoutClass)
        } else {
            addRuleSet(verticalScrollLayoutClass)
        }
    }

    companion object {
        val verticalScrollLayoutClass = classRuleSet {
            isScrollableVertically = true
            isScrollableHorizontally = false
        }
        val horizontalScrollLayoutClass = classRuleSet {
            isScrollableHorizontally = true
            isScrollableVertically = false
        }
    }
}

