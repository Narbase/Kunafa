package com.narbase.kunafa.core.components


fun <V : View> V.visit(page: Page, setup: V.() -> Unit): V {
    this.addToParent()
    configureElement(page)
    this.setup()
    return this
}
