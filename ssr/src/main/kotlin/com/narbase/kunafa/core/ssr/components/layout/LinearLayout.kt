@file:Suppress("unused")

package com.narbase.kunafa.core.ssr.components.layout

import com.narbase.kunafa.core.components.layout.LinearLayoutOrientation
import com.narbase.kunafa.core.components.layout.LinearLayoutOrientation.Horizontal
import com.narbase.kunafa.core.ssr.components.Page
import com.narbase.kunafa.core.ssr.components.View

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
open class LinearLayout(
        parent: View?,
        private val initialOrientation: LinearLayoutOrientation? = Horizontal
) : View(parent) {

    override fun configureElement(page: Page<*>) {
        super.configureElement(page)
        if (initialOrientation == Horizontal) {
            addRuleSet(page.horizontalLayoutClass)
        } else {
            addRuleSet(page.verticalLayoutClass)
        }
        addRuleSet(page.linearLayoutClass)
    }

}

