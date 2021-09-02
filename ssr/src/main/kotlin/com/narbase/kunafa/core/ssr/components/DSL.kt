@file:Suppress("unused")

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


fun View.textInput(block: TextInput.() -> Unit): TextInput {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TextInput(this).visit(page, block)
}

fun View.button(block: Button.() -> Unit): Button {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Button(this).visit(page, block)
}

fun View.imageView(block: ImageView.() -> Unit): ImageView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ImageView(this).visit(page, block)
}

fun View.checkbox(block: Checkbox.() -> Unit): Checkbox {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Checkbox(this).visit(page, block)
}

fun View.table(block: Table.() -> Unit): Table {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Table(this).visit(page, block)
}

fun Table.tableHeader(block: TableHeader.() -> Unit): TableHeader {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableHeader(this).visit(page, block)
}

fun Table.tableBody(block: TableBody.() -> Unit): TableBody {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableBody(this).visit(page, block)
}

fun Table.tableFooter(block: TableFooter.() -> Unit): TableFooter {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableFooter(this).visit(page, block)
}

fun Table.row(block: TableRow.() -> Unit): TableRow {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableRow(this).visit(page, block)
}

fun TableHeader.row(block: TableRow.() -> Unit): TableRow {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableRow(this).visit(page, block)
}

fun TableFooter.row(block: TableRow.() -> Unit): TableRow {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableRow(this).visit(page, block)
}

fun TableBody.row(block: TableRow.() -> Unit): TableRow {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableRow(this).visit(page, block)
}

fun TableRow.cell(block: TableCell.() -> Unit): TableCell {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableCell(this).visit(page, block)
}

fun TableRow.headerCell(block: TableHeaderCell.() -> Unit): TableHeaderCell {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return TableHeaderCell(this).visit(page, block)
}

fun View.form(block: Form.() -> Unit): Form {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Form(this).visit(page, block)
}

fun View.fieldSet(block: FieldSet.() -> Unit): FieldSet {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return FieldSet(this).visit(page, block)
}

fun View.legend(block: Legend.() -> Unit): Legend {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Legend(this).visit(page, block)
}

fun View.radio(block: Radio.() -> Unit): Radio {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Radio(this).visit(page, block)
}

fun View.ul(block: UList.() -> Unit = {}): UList {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return UList(this).visit(page, block)
}

fun View.li(block: ListItem.() -> Unit = {}): ListItem {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ListItem(this).visit(page, block)
}
