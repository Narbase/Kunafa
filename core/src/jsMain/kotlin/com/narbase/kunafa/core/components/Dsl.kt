@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Grid
import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.components.layout.LinearLayoutOrientation
import com.narbase.kunafa.core.components.layout.ScrollView
import com.narbase.kunafa.core.lifecycle.LifecycleObserver
import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
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
    return LinearLayout(this, LinearLayoutOrientation.Vertical).visit(lifecycleObserver, block)
}

fun View?.horizontalLayout(lifecycleObserver: LifecycleObserver? = null, block: LinearLayout.() -> Unit): LinearLayout {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return LinearLayout(this, LinearLayoutOrientation.Horizontal).visit(lifecycleObserver, block)
}

fun View.grid(lifecycleObserver: LifecycleObserver? = null, block: Grid.() -> Unit): Grid {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Grid(this).visit(lifecycleObserver, block)
}

fun View?.horizontalScrollLayout(lifecycleObserver: LifecycleObserver? = null, block: ScrollView.() -> Unit): ScrollView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ScrollView(this, LinearLayoutOrientation.Horizontal).visit(lifecycleObserver, block)
}

fun View?.verticalScrollLayout(lifecycleObserver: LifecycleObserver? = null, block: ScrollView.() -> Unit): ScrollView {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ScrollView(this, LinearLayoutOrientation.Vertical).visit(lifecycleObserver, block)
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

fun View?.button(lifecycleObserver: LifecycleObserver? = null, element: HTMLButtonElement = document.createElement("button") as HTMLButtonElement, block: Button.() -> Unit): Button {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Button(this, element).visit(lifecycleObserver, block)
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


/* Auto generated */

fun View.abbr(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "abbr").visit(lifecycleObserver, block)

fun View.address(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "address").visit(lifecycleObserver, block)

fun View.area(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "area").visit(lifecycleObserver, block)

fun View.article(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "article").visit(lifecycleObserver, block)

fun View.aside(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "aside").visit(lifecycleObserver, block)

fun View.audio(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "audio").visit(lifecycleObserver, block)

fun View.b(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "b").visit(lifecycleObserver, block)

fun View.base(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "base").visit(lifecycleObserver, block)

fun View.bdi(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "bdi").visit(lifecycleObserver, block)

fun View.bdo(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "bdo").visit(lifecycleObserver, block)

fun View.blockquote(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "blockquote").visit(lifecycleObserver, block)

fun View.br(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "br").visit(lifecycleObserver, block)

fun View.canvas(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "canvas").visit(lifecycleObserver, block)

fun View.caption(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "caption").visit(lifecycleObserver, block)

fun View.cite(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "cite").visit(lifecycleObserver, block)

fun View.code(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "code").visit(lifecycleObserver, block)

fun View.col(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "col").visit(lifecycleObserver, block)

fun View.colgroup(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "colgroup").visit(lifecycleObserver, block)

fun View.data(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "data").visit(lifecycleObserver, block)

fun View.datalist(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "datalist").visit(lifecycleObserver, block)

fun View.dd(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "dd").visit(lifecycleObserver, block)

fun View.del(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "del").visit(lifecycleObserver, block)

fun View.details(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "details").visit(lifecycleObserver, block)

fun View.dfn(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "dfn").visit(lifecycleObserver, block)

fun View.dialog(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "dialog").visit(lifecycleObserver, block)

fun View.div(lifecycleObserver: LifecycleObserver? = null, block: View.() -> Unit = {}) = View(this).visit(lifecycleObserver, block)

fun View.dl(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "dl").visit(lifecycleObserver, block)

fun View.dt(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "dt").visit(lifecycleObserver, block)

fun View.em(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "em").visit(lifecycleObserver, block)

fun View.embed(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "embed").visit(lifecycleObserver, block)

fun View.figcaption(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "figcaption").visit(lifecycleObserver, block)

fun View.figure(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "figure").visit(lifecycleObserver, block)

fun View.footer(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "footer").visit(lifecycleObserver, block)

fun View.h1(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "h1").visit(lifecycleObserver, block)
fun View.h2(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "h2").visit(lifecycleObserver, block)
fun View.h3(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "h3").visit(lifecycleObserver, block)
fun View.h4(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "h4").visit(lifecycleObserver, block)
fun View.h5(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "h5").visit(lifecycleObserver, block)
fun View.h6(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "h6").visit(lifecycleObserver, block)

fun View.head(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "head").visit(lifecycleObserver, block)

fun View.header(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "header").visit(lifecycleObserver, block)

fun View.hr(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "hr").visit(lifecycleObserver, block)

fun View.i(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "i").visit(lifecycleObserver, block)

fun View.iframe(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "iframe").visit(lifecycleObserver, block)

fun View.input(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "input").visit(lifecycleObserver, block)

fun View.ins(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "ins").visit(lifecycleObserver, block)

fun View.kbd(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "kbd").visit(lifecycleObserver, block)

fun View.label(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "label").visit(lifecycleObserver, block)

fun View.link(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "link").visit(lifecycleObserver, block)

fun View.main(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "main").visit(lifecycleObserver, block)

fun View.map(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "map").visit(lifecycleObserver, block)

fun View.mark(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "mark").visit(lifecycleObserver, block)

fun View.meta(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "meta").visit(lifecycleObserver, block)

fun View.meter(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "meter").visit(lifecycleObserver, block)

fun View.nav(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "nav").visit(lifecycleObserver, block)

fun View.noscript(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "noscript").visit(lifecycleObserver, block)

fun View.objectElement(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "object").visit(lifecycleObserver, block)

fun View.ol(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "ol").visit(lifecycleObserver, block)

fun View.optgroup(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "optgroup").visit(lifecycleObserver, block)

fun View.option(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "option").visit(lifecycleObserver, block)

fun View.output(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "output").visit(lifecycleObserver, block)

fun View.p(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "p").visit(lifecycleObserver, block)

fun View.param(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "param").visit(lifecycleObserver, block)

fun View.picture(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "picture").visit(lifecycleObserver, block)

fun View.pre(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "pre").visit(lifecycleObserver, block)

fun View.progress(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "progress").visit(lifecycleObserver, block)

fun View.q(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "q").visit(lifecycleObserver, block)

fun View.rp(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "rp").visit(lifecycleObserver, block)

fun View.rt(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "rt").visit(lifecycleObserver, block)

fun View.ruby(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "ruby").visit(lifecycleObserver, block)

fun View.s(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "s").visit(lifecycleObserver, block)

fun View.samp(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "samp").visit(lifecycleObserver, block)

fun View.script(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "script").visit(lifecycleObserver, block)

fun View.section(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "section").visit(lifecycleObserver, block)

fun View.select(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "select").visit(lifecycleObserver, block)

fun View.small(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "small").visit(lifecycleObserver, block)

fun View.source(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "source").visit(lifecycleObserver, block)

fun View.span(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "span").visit(lifecycleObserver, block)

fun View.strong(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "strong").visit(lifecycleObserver, block)

fun View.style(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "style").visit(lifecycleObserver, block)

fun View.sub(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "sub").visit(lifecycleObserver, block)

fun View.summary(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "summary").visit(lifecycleObserver, block)

fun View.sup(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "sup").visit(lifecycleObserver, block)

fun View.svg(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "svg").visit(lifecycleObserver, block)

fun View.tbody(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "tbody").visit(lifecycleObserver, block)

fun View.td(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "td").visit(lifecycleObserver, block)

fun View.template(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "template").visit(lifecycleObserver, block)

fun View.textarea(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "textarea").visit(lifecycleObserver, block)

fun View.time(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "time").visit(lifecycleObserver, block)

fun View.title(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "title").visit(lifecycleObserver, block)

fun View.tr(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "tr").visit(lifecycleObserver, block)

fun View.track(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "track").visit(lifecycleObserver, block)

fun View.u(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "u").visit(lifecycleObserver, block)

fun View.varElement(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "var").visit(lifecycleObserver, block)

fun View.video(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "video").visit(lifecycleObserver, block)

fun View.wbr(lifecycleObserver: LifecycleObserver? = null, block: CustomView.() -> Unit = {}) = CustomView(this, "wbr").visit(lifecycleObserver, block)

