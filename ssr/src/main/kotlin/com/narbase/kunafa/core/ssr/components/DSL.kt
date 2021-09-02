package com.narbase.kunafa.core.ssr.components

import com.narbase.kunafa.core.components.layout.LinearLayoutOrientation
import com.narbase.kunafa.core.ssr.components.layout.LinearLayout
import com.narbase.kunafa.core.ssr.components.layout.ScrollView

fun page(block: Page.() -> Unit = {}): Page {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    val page = Page()
    page.prepare()
    page.visit(page, block)
    return page
}


fun View.linearLayout(block: LinearLayout.() -> Unit): LinearLayout {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return LinearLayout(this, null).visit(page, block)
}

fun View.verticalLayout(block: LinearLayout.() -> Unit): LinearLayout {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return LinearLayout(this, LinearLayoutOrientation.Vertical).visit(page, block)
}

fun View.horizontalLayout(block: LinearLayout.() -> Unit): LinearLayout {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return LinearLayout(this, LinearLayoutOrientation.Horizontal).visit(page, block)
}

fun View.horizontalScrollLayout(block: ScrollView.() -> Unit): ScrollView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ScrollView(this, LinearLayoutOrientation.Horizontal).visit(page, block)
}

fun View.verticalScrollLayout(block: ScrollView.() -> Unit): ScrollView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ScrollView(this, LinearLayoutOrientation.Vertical).visit(page, block)
}


fun View.view(block: View.() -> Unit): View {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return View(this).visit(page, block)
}

fun View.textView(block: TextView.() -> Unit): TextView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TextView(this).visit(page, block)
}

fun View.a(block: Anchor.() -> Unit): Anchor {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Anchor(this).visit(page, block)
}