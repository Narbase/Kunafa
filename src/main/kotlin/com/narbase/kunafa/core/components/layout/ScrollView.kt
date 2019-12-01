package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.BaseElement
import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.css.classRuleSet
import com.narbase.kunafa.core.css.isScrollableHorizontally
import com.narbase.kunafa.core.css.isScrollableVertically

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */
class ScrollView(
        parent: BaseElement?,
        private val initialOrientation: Orientation?
) : LinearLayout(parent, initialOrientation) {

    override fun configureElement() {
        super.configureElement()
        if (initialOrientation == Orientation.Horizontal) {
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

