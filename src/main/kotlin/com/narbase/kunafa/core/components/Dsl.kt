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
    Page.prepare()
    Page.visit(lifecycleObserver, block)
}

fun BaseElement?.linearLayout(lifecycleObserver: LifecycleObserver? = null, block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, null).visit(lifecycleObserver, block)
fun BaseElement?.verticalLayout(lifecycleObserver: LifecycleObserver? = null, block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Vertical).visit(lifecycleObserver, block)
fun BaseElement?.horizontalLayout(lifecycleObserver: LifecycleObserver? = null, block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Horizontal).visit(lifecycleObserver, block)
fun BaseElement?.horizontalScrollLayout(lifecycleObserver: LifecycleObserver? = null, block: ScrollView.() -> Unit): ScrollView = ScrollView(this, LinearLayout.Orientation.Horizontal).visit(lifecycleObserver, block)
fun BaseElement?.verticalScrollLayout(lifecycleObserver: LifecycleObserver? = null, block: ScrollView.() -> Unit): ScrollView = ScrollView(this, LinearLayout.Orientation.Vertical).visit(lifecycleObserver, block)

//fun View?.anchorLayout(lifecycleObserver: LifecycleObserver? = null, block: AnchorLayout.() -> Unit): AnchorLayout = AnchorLayout(this).visit(lifecycleObserver, block)

fun View?.view(lifecycleObserver: LifecycleObserver? = null, block: View.() -> Unit): View = View(this).visit(lifecycleObserver, block)
fun BaseElement?.baseElement(lifecycleObserver: LifecycleObserver? = null, block: BaseElement.() -> Unit): BaseElement = BaseElement(this).visit(lifecycleObserver, block)
fun BaseElement?.a(lifecycleObserver: LifecycleObserver? = null, block: Anchor.() -> Unit): Anchor = Anchor(this).visit(lifecycleObserver, block)
fun BaseElement?.textView(lifecycleObserver: LifecycleObserver? = null, block: TextView.() -> Unit): TextView = TextView(this).visit(lifecycleObserver, block)
fun BaseElement?.textInput(lifecycleObserver: LifecycleObserver? = null, block: TextInput.() -> Unit): TextInput = TextInput(this).visit(lifecycleObserver, block)
fun BaseElement?.button(lifecycleObserver: LifecycleObserver? = null, block: Button.() -> Unit): Button = Button(this).visit(lifecycleObserver, block)
fun BaseElement?.imageView(lifecycleObserver: LifecycleObserver? = null, block: ImageView.() -> Unit): ImageView = ImageView(this).visit(lifecycleObserver, block)
fun BaseElement?.checkbox(lifecycleObserver: LifecycleObserver? = null, block: Checkbox.() -> Unit): Checkbox = Checkbox(this).visit(lifecycleObserver, block)

fun BaseElement?.table(lifecycleObserver: LifecycleObserver? = null, block: Table.() -> Unit): Table = Table(this).visit(lifecycleObserver, block)
fun Table?.tableHeader(lifecycleObserver: LifecycleObserver? = null, block: TableHeader.() -> Unit): TableHeader = TableHeader(this).visit(lifecycleObserver, block)
fun Table?.tableBody(lifecycleObserver: LifecycleObserver? = null, block: TableBody.() -> Unit): TableBody = TableBody(this).visit(lifecycleObserver, block)
fun Table?.tableFooter(lifecycleObserver: LifecycleObserver? = null, block: TableFooter.() -> Unit): TableFooter = TableFooter(this).visit(lifecycleObserver, block)

fun Table?.row(lifecycleObserver: LifecycleObserver? = null, block: TableRow.() -> Unit): TableRow = TableRow(this).visit(lifecycleObserver, block)
fun TableHeader?.row(lifecycleObserver: LifecycleObserver? = null, block: TableRow.() -> Unit): TableRow = TableRow(this).visit(lifecycleObserver, block)
fun TableFooter?.row(lifecycleObserver: LifecycleObserver? = null, block: TableRow.() -> Unit): TableRow = TableRow(this).visit(lifecycleObserver, block)
fun TableBody?.row(lifecycleObserver: LifecycleObserver? = null, block: TableRow.() -> Unit): TableRow = TableRow(this).visit(lifecycleObserver, block)
fun TableRow?.cell(lifecycleObserver: LifecycleObserver? = null, block: TableCell.() -> Unit): TableCell = TableCell(this).visit(lifecycleObserver, block)
fun TableRow?.headerCell(lifecycleObserver: LifecycleObserver? = null, block: TableHeaderCell.() -> Unit): TableHeaderCell = TableHeaderCell(this).visit(lifecycleObserver, block)

fun BaseElement?.form(lifecycleObserver: LifecycleObserver? = null, block: Form.() -> Unit): Form = Form(this).visit(lifecycleObserver, block)
fun BaseElement?.fieldSet(lifecycleObserver: LifecycleObserver? = null, block: FieldSet.() -> Unit): FieldSet = FieldSet(this).visit(lifecycleObserver, block)
fun BaseElement?.legend(lifecycleObserver: LifecycleObserver? = null, block: Legend.() -> Unit): Legend = Legend(this).visit(lifecycleObserver, block)
fun BaseElement?.radio(lifecycleObserver: LifecycleObserver? = null, block: Radio.() -> Unit): Radio = Radio(this).visit(lifecycleObserver, block)

fun BaseElement?.ul(lifecycleObserver: LifecycleObserver? = null, block: (UList.() -> Unit)? = null): UList =
        UList(this).visit(lifecycleObserver, block ?: {})

fun BaseElement?.li(lifecycleObserver: LifecycleObserver? = null, block: (ListItem.() -> Unit)? = null): ListItem =
        ListItem(this).visit(lifecycleObserver, block ?: {})

val detached: View? = null