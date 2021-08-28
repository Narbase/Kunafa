package com.narbase.kunafa.core.ssr.components

fun page(block: View.() -> Unit = {}): Page {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    val page = Page()
    page.visit(block)
    return page
}


fun View?.view(block: View.() -> Unit): View {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return View(this).visit(block)
}