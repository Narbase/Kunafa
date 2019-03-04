@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.Page.visit
import com.narbase.kunafa.core.components.layout.*
import com.narbase.kunafa.core.css.RuleSet

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

fun page(setupAndAddChildren: View.() -> Unit = {}) {
    Page.prepare()
    Page.visit(null, setupAndAddChildren)
}

fun detachedView(rules: (RuleSet.() -> Unit)? = null, block: DetachedView.() -> Unit): DetachedView = DetachedView().visit(rules, block)
fun View.linearLayout(rules: (RuleSet.() -> Unit)? = null, block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, null).visit(rules, block)
fun View.verticalLayout(rules: (RuleSet.() -> Unit)? = null, block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Vertical).visit(rules, block)
fun View.horizontalLayout(rules: (RuleSet.() -> Unit)? = null, block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Horizontal).visit(rules, block)
fun View.horizontalScrollView(rules: (RuleSet.() -> Unit)? = null, block: ScrollView.() -> Unit): ScrollView = ScrollView(this, LinearLayout.Orientation.Horizontal).visit(rules, block)
fun View.verticalScrollView(rules: (RuleSet.() -> Unit)? = null, block: ScrollView.() -> Unit): ScrollView = ScrollView(this, LinearLayout.Orientation.Vertical).visit(rules, block)

fun View.anchorLayout(rules: (RuleSet.() -> Unit)? = null, block: AnchorLayout.() -> Unit): AnchorLayout = AnchorLayout(this).visit(rules, block)
fun View.viewView(rules: (RuleSet.() -> Unit)? = null, block: ViewView.() -> Unit): ViewView = ViewView(this).visit(rules, block)

fun View.view(rules: (RuleSet.() -> Unit)? = null, block: View.() -> Unit): View = View(this).visit(rules, block)
fun View.textView(rules: (RuleSet.() -> Unit)? = null, block: TextView.() -> Unit): TextView = TextView(this).visit(rules, block)
fun View.textInput(rules: (RuleSet.() -> Unit)? = null, block: TextInput.() -> Unit): TextInput = TextInput(this).visit(rules, block)
fun View.button(rules: (RuleSet.() -> Unit)? = null, block: ButtonView.() -> Unit): ButtonView = ButtonView(this).visit(rules, block)
fun View.imageView(rules: (RuleSet.() -> Unit)? = null, block: ImageView.() -> Unit): ImageView = ImageView(this).visit(rules, block)
fun View.checkbox(rules: (RuleSet.() -> Unit)? = null, block: Checkbox.() -> Unit): Checkbox = Checkbox(this).visit(rules, block)

fun View.table(block: Table.() -> Unit): Table = Table(this).visit(null, block)
fun Table.tableHeader(block: TableHeader.() -> Unit): TableHeader = TableHeader(this).visit(null, block)
fun Table.tableBody(block: TableBody.() -> Unit): TableBody = TableBody(this).visit(null, block)
fun Table.tableFooter(block: TableFooter.() -> Unit): TableFooter = TableFooter(this).visit(null, block)

fun Table.row(block: TableRow.() -> Unit): TableRow = TableRow(this).visit(null, block)
fun TableHeader.row(block: TableRow.() -> Unit): TableRow = TableRow(this).visit(null, block)
fun TableFooter.row(block: TableRow.() -> Unit): TableRow = TableRow(this).visit(null, block)
fun TableBody.row(block: TableRow.() -> Unit): TableRow = TableRow(this).visit(null, block)
fun TableRow.cell(block: TableCell.() -> Unit): TableCell = TableCell(this).visit(null, block)
fun TableRow.headerCell(block: TableHeaderCell.() -> Unit): TableHeaderCell = TableHeaderCell(this).visit(null, block)

fun View.form(rules: (RuleSet.() -> Unit)? = null, block: Form.() -> Unit): Form = Form(this).visit(rules, block)
fun View.fieldSet(rules: (RuleSet.() -> Unit)? = null, block: FieldSet.() -> Unit): FieldSet = FieldSet(this).visit(rules, block)
fun View.legend(rules: (RuleSet.() -> Unit)? = null, block: Legend.() -> Unit): Legend = Legend(this).visit(rules, block)
fun View.radio(rules: (RuleSet.() -> Unit)? = null, block: Radio.() -> Unit): Radio = Radio(this).visit(rules, block)
