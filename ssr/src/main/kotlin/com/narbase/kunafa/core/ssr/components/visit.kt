package com.narbase.kunafa.core.ssr.components


fun <V : View> V.visit(setup: V.() -> Unit): V {
    this.addToParent()
    this.setup()
    return this
}
