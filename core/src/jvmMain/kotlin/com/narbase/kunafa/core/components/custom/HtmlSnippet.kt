package com.narbase.kunafa.core.components.custom

import com.narbase.kunafa.core.components.Page
import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.visit


class HtmlSnippet(parent: View? = null, private val internalHtml: String) : View(parent) {

    override fun configureElement(page: Page) {}

    override fun build(): String {
        isBuilt = true
        return internalHtml
    }
}

fun Page.htmlSnippet(snippet: String): String {
    HtmlSnippet(this, snippet).visit(page) { }
    return snippet
}