@file:Suppress("unused")

package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.Page
import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.layout.LinearLayoutOrientation.Horizontal

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
actual open class LinearLayout(
        parent: View?,
        private val initialOrientation: LinearLayoutOrientation? = Horizontal
) : View(parent) {

    override fun configureElement() {
        super.configureElement()
        if (initialOrientation == Horizontal) {
            addRuleSet(Page.baseStyles.horizontalLayoutClass)
        } else {
            addRuleSet(Page.baseStyles.verticalLayoutClass)
        }
        addRuleSet(Page.baseStyles.linearLayoutClass)
    }

}

