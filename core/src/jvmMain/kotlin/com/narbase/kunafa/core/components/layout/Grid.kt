package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.Page
import com.narbase.kunafa.core.components.View

actual class Grid(parent: View?) : View(parent) {

    override fun configureElement(page: Page) {
        super.configureElement(page)
        addRuleSet(page.gridLayoutClass)
    }

}