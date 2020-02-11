@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.components.layout.ScrollView
import com.narbase.kunafa.core.lifecycle.LifecycleObserver

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

fun page(lifecycleObserver: LifecycleObserver? = null, block: View.() -> Unit = {}) {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    Page.prepare()
    Page.visit(lifecycleObserver, block)
}

fun View?.linearLayout(lifecycleObserver: LifecycleObserver? = null, block: LinearLayout.() -> Unit): LinearLayout {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return LinearLayout(this, null).visit(lifecycleObserver, block)
}

fun View?.verticalLayout(lifecycleObserver: LifecycleObserver? = null, block: LinearLayout.() -> Unit): LinearLayout {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return LinearLayout(this, LinearLayout.Orientation.Vertical).visit(lifecycleObserver, block)
}

fun View?.horizontalLayout(lifecycleObserver: LifecycleObserver? = null, block: LinearLayout.() -> Unit): LinearLayout {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return LinearLayout(this, LinearLayout.Orientation.Horizontal).visit(lifecycleObserver, block)
}

fun View?.horizontalScrollLayout(lifecycleObserver: LifecycleObserver? = null, block: ScrollView.() -> Unit): ScrollView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ScrollView(this, LinearLayout.Orientation.Horizontal).visit(lifecycleObserver, block)
}

fun View?.verticalScrollLayout(lifecycleObserver: LifecycleObserver? = null, block: ScrollView.() -> Unit): ScrollView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ScrollView(this, LinearLayout.Orientation.Vertical).visit(lifecycleObserver, block)
}

fun View?.view(lifecycleObserver: LifecycleObserver? = null, block: View.() -> Unit): View {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return View(this).visit(lifecycleObserver, block)
}

fun View?.a(lifecycleObserver: LifecycleObserver? = null, block: Anchor.() -> Unit): Anchor {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Anchor(this).visit(lifecycleObserver, block)
}

fun View?.textView(lifecycleObserver: LifecycleObserver? = null, block: TextView.() -> Unit): TextView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TextView(this).visit(lifecycleObserver, block)
}

fun View?.textInput(lifecycleObserver: LifecycleObserver? = null, block: TextInput.() -> Unit): TextInput {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TextInput(this).visit(lifecycleObserver, block)
}

fun View?.button(lifecycleObserver: LifecycleObserver? = null, block: Button.() -> Unit): Button {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Button(this).visit(lifecycleObserver, block)
}

fun View?.imageView(lifecycleObserver: LifecycleObserver? = null, block: ImageView.() -> Unit): ImageView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ImageView(this).visit(lifecycleObserver, block)
}

fun View?.checkbox(lifecycleObserver: LifecycleObserver? = null, block: Checkbox.() -> Unit): Checkbox {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Checkbox(this).visit(lifecycleObserver, block)
}

fun View?.table(lifecycleObserver: LifecycleObserver? = null, block: Table.() -> Unit): Table {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Table(this).visit(lifecycleObserver, block)
}

fun Table?.tableHeader(lifecycleObserver: LifecycleObserver? = null, block: TableHeader.() -> Unit): TableHeader {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableHeader(this).visit(lifecycleObserver, block)
}

fun Table?.tableBody(lifecycleObserver: LifecycleObserver? = null, block: TableBody.() -> Unit): TableBody {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableBody(this).visit(lifecycleObserver, block)
}

fun Table?.tableFooter(lifecycleObserver: LifecycleObserver? = null, block: TableFooter.() -> Unit): TableFooter {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableFooter(this).visit(lifecycleObserver, block)
}

fun Table?.row(lifecycleObserver: LifecycleObserver? = null, block: TableRow.() -> Unit): TableRow {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableRow(this).visit(lifecycleObserver, block)
}

fun TableHeader?.row(lifecycleObserver: LifecycleObserver? = null, block: TableRow.() -> Unit): TableRow {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableRow(this).visit(lifecycleObserver, block)
}

fun TableFooter?.row(lifecycleObserver: LifecycleObserver? = null, block: TableRow.() -> Unit): TableRow {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableRow(this).visit(lifecycleObserver, block)
}

fun TableBody?.row(lifecycleObserver: LifecycleObserver? = null, block: TableRow.() -> Unit): TableRow {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableRow(this).visit(lifecycleObserver, block)
}

fun TableRow?.cell(lifecycleObserver: LifecycleObserver? = null, block: TableCell.() -> Unit): TableCell {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableCell(this).visit(lifecycleObserver, block)
}

fun TableRow?.headerCell(lifecycleObserver: LifecycleObserver? = null, block: TableHeaderCell.() -> Unit): TableHeaderCell {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableHeaderCell(this).visit(lifecycleObserver, block)
}

fun View?.form(lifecycleObserver: LifecycleObserver? = null, block: Form.() -> Unit): Form {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Form(this).visit(lifecycleObserver, block)
}

fun View?.fieldSet(lifecycleObserver: LifecycleObserver? = null, block: FieldSet.() -> Unit): FieldSet {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return FieldSet(this).visit(lifecycleObserver, block)
}

fun View?.legend(lifecycleObserver: LifecycleObserver? = null, block: Legend.() -> Unit): Legend {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Legend(this).visit(lifecycleObserver, block)
}

fun View?.radio(lifecycleObserver: LifecycleObserver? = null, block: Radio.() -> Unit): Radio {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Radio(this).visit(lifecycleObserver, block)
}

fun View?.ul(lifecycleObserver: LifecycleObserver? = null, block: UList.() -> Unit = {}): UList {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return UList(this).visit(lifecycleObserver, block)
}

fun View?.li(lifecycleObserver: LifecycleObserver? = null, block: ListItem.() -> Unit = {}): ListItem {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ListItem(this).visit(lifecycleObserver, block)
}

val detached: View? = null