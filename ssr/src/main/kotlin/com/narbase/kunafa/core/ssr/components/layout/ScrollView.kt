package com.narbase.kunafa.core.ssr.components.layout

import com.narbase.kunafa.core.components.layout.LinearLayoutOrientation
import com.narbase.kunafa.core.ssr.components.Page
import com.narbase.kunafa.core.ssr.components.View

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class ScrollView(
        parent: View?,
        private val initialOrientation: LinearLayoutOrientation?
) : LinearLayout(parent, initialOrientation) {

    override fun configureElement(page: Page) {
        super.configureElement(page)
        if (initialOrientation == LinearLayoutOrientation.Horizontal) {
            addRuleSet(page.horizontalScrollLayoutClass)
        } else {
            addRuleSet(page.verticalScrollLayoutClass)
        }
    }

}

