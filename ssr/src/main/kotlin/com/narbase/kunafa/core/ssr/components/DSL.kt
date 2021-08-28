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

fun View?.textView(block: TextView.() -> Unit): TextView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TextView(this).visit(block)
}

fun View?.a(block: Anchor.() -> Unit): Anchor {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Anchor(this).visit(block)
}