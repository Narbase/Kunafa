@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Grid
import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.components.layout.LinearLayoutOrientation
import com.narbase.kunafa.core.components.layout.ScrollView

fun page(block: Page.() -> Unit = {}): Page {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    val page = Page()
    page.prepare()
    page.visit(page, block)
    return page
}

inline fun <reified H : Any> page(clientReference: H, crossinline block: Page.(ref: H) -> Unit = {}): Page {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    val page = Page()
    page.ref = H::class.simpleName
    page.prepare()
    val newBlock: Page.() -> Unit = { block(clientReference) }
    page.visit(page, newBlock)
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

fun View.grid(block: Grid.() -> Unit): Grid {
//    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Grid(this).visit(page, block)
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

/* Auto generated */

fun View.abbr(block: CustomView.() -> Unit = {}) = CustomView(this, "abbr").visit(page, block)

fun View.address(block: CustomView.() -> Unit = {}) = CustomView(this, "address").visit(page, block)

fun View.area(block: CustomView.() -> Unit = {}) = CustomView(this, "area").visit(page, block)

fun View.article(block: CustomView.() -> Unit = {}) = CustomView(this, "article").visit(page, block)

fun View.aside(block: CustomView.() -> Unit = {}) = CustomView(this, "aside").visit(page, block)

fun View.audio(block: CustomView.() -> Unit = {}) = CustomView(this, "audio").visit(page, block)

fun View.b(block: CustomView.() -> Unit = {}) = CustomView(this, "b").visit(page, block)

fun View.base(block: CustomView.() -> Unit = {}) = CustomView(this, "base").visit(page, block)

fun View.bdi(block: CustomView.() -> Unit = {}) = CustomView(this, "bdi").visit(page, block)

fun View.bdo(block: CustomView.() -> Unit = {}) = CustomView(this, "bdo").visit(page, block)

fun View.blockquote(block: CustomView.() -> Unit = {}) = CustomView(this, "blockquote").visit(page, block)

fun View.br(block: CustomView.() -> Unit = {}) = CustomView(this, "br").visit(page, block)

fun View.canvas(block: CustomView.() -> Unit = {}) = CustomView(this, "canvas").visit(page, block)

fun View.caption(block: CustomView.() -> Unit = {}) = CustomView(this, "caption").visit(page, block)

fun View.cite(block: CustomView.() -> Unit = {}) = CustomView(this, "cite").visit(page, block)

fun View.code(block: CustomView.() -> Unit = {}) = CustomView(this, "code").visit(page, block)

fun View.col(block: CustomView.() -> Unit = {}) = CustomView(this, "col").visit(page, block)

fun View.colgroup(block: CustomView.() -> Unit = {}) = CustomView(this, "colgroup").visit(page, block)

fun View.data(block: CustomView.() -> Unit = {}) = CustomView(this, "data").visit(page, block)

fun View.datalist(block: CustomView.() -> Unit = {}) = CustomView(this, "datalist").visit(page, block)

fun View.dd(block: CustomView.() -> Unit = {}) = CustomView(this, "dd").visit(page, block)

fun View.del(block: CustomView.() -> Unit = {}) = CustomView(this, "del").visit(page, block)

fun View.details(block: CustomView.() -> Unit = {}) = CustomView(this, "details").visit(page, block)

fun View.dfn(block: CustomView.() -> Unit = {}) = CustomView(this, "dfn").visit(page, block)

fun View.dialog(block: CustomView.() -> Unit = {}) = CustomView(this, "dialog").visit(page, block)

fun View.div(block: View.() -> Unit = {}) = View(this).visit(page, block)

fun View.dl(block: CustomView.() -> Unit = {}) = CustomView(this, "dl").visit(page, block)

fun View.dt(block: CustomView.() -> Unit = {}) = CustomView(this, "dt").visit(page, block)

fun View.em(block: CustomView.() -> Unit = {}) = CustomView(this, "em").visit(page, block)

fun View.embed(block: CustomView.() -> Unit = {}) = CustomView(this, "embed").visit(page, block)

fun View.figcaption(block: CustomView.() -> Unit = {}) = CustomView(this, "figcaption").visit(page, block)

fun View.figure(block: CustomView.() -> Unit = {}) = CustomView(this, "figure").visit(page, block)

fun View.footer(block: CustomView.() -> Unit = {}) = CustomView(this, "footer").visit(page, block)

fun View.h1(block: CustomView.() -> Unit = {}) = CustomView(this, "h1").visit(page, block)
fun View.h2(block: CustomView.() -> Unit = {}) = CustomView(this, "h2").visit(page, block)
fun View.h3(block: CustomView.() -> Unit = {}) = CustomView(this, "h3").visit(page, block)
fun View.h4(block: CustomView.() -> Unit = {}) = CustomView(this, "h4").visit(page, block)
fun View.h5(block: CustomView.() -> Unit = {}) = CustomView(this, "h5").visit(page, block)
fun View.h6(block: CustomView.() -> Unit = {}) = CustomView(this, "h6").visit(page, block)

fun View.head(block: CustomView.() -> Unit = {}) = CustomView(this, "head").visit(page, block)

fun View.header(block: CustomView.() -> Unit = {}) = CustomView(this, "header").visit(page, block)

fun View.hr(block: CustomView.() -> Unit = {}) = CustomView(this, "hr").visit(page, block)

fun View.i(block: CustomView.() -> Unit = {}) = CustomView(this, "i").visit(page, block)

fun View.iframe(block: CustomView.() -> Unit = {}) = CustomView(this, "iframe").visit(page, block)

fun View.input(block: CustomView.() -> Unit = {}) = CustomView(this, "input").visit(page, block)

fun View.ins(block: CustomView.() -> Unit = {}) = CustomView(this, "ins").visit(page, block)

fun View.kbd(block: CustomView.() -> Unit = {}) = CustomView(this, "kbd").visit(page, block)

fun View.label(block: CustomView.() -> Unit = {}) = CustomView(this, "label").visit(page, block)

fun View.link(block: CustomView.() -> Unit = {}) = CustomView(this, "link").visit(page, block)

fun View.main(block: CustomView.() -> Unit = {}) = CustomView(this, "main").visit(page, block)

fun View.map(block: CustomView.() -> Unit = {}) = CustomView(this, "map").visit(page, block)

fun View.mark(block: CustomView.() -> Unit = {}) = CustomView(this, "mark").visit(page, block)

fun View.meta(block: CustomView.() -> Unit = {}) = CustomView(this, "meta").visit(page, block)

fun View.meter(block: CustomView.() -> Unit = {}) = CustomView(this, "meter").visit(page, block)

fun View.nav(block: CustomView.() -> Unit = {}) = CustomView(this, "nav").visit(page, block)

fun View.noscript(block: CustomView.() -> Unit = {}) = CustomView(this, "noscript").visit(page, block)

fun View.objectElement(block: CustomView.() -> Unit = {}) = CustomView(this, "object").visit(page, block)

fun View.ol(block: CustomView.() -> Unit = {}) = CustomView(this, "ol").visit(page, block)

fun View.optgroup(block: CustomView.() -> Unit = {}) = CustomView(this, "optgroup").visit(page, block)

fun View.option(block: CustomView.() -> Unit = {}) = CustomView(this, "option").visit(page, block)

fun View.output(block: CustomView.() -> Unit = {}) = CustomView(this, "output").visit(page, block)

fun View.p(block: CustomView.() -> Unit = {}) = CustomView(this, "p").visit(page, block)

fun View.param(block: CustomView.() -> Unit = {}) = CustomView(this, "param").visit(page, block)

fun View.picture(block: CustomView.() -> Unit = {}) = CustomView(this, "picture").visit(page, block)

fun View.pre(block: CustomView.() -> Unit = {}) = CustomView(this, "pre").visit(page, block)

fun View.progress(block: CustomView.() -> Unit = {}) = CustomView(this, "progress").visit(page, block)

fun View.q(block: CustomView.() -> Unit = {}) = CustomView(this, "q").visit(page, block)

fun View.rp(block: CustomView.() -> Unit = {}) = CustomView(this, "rp").visit(page, block)

fun View.rt(block: CustomView.() -> Unit = {}) = CustomView(this, "rt").visit(page, block)

fun View.ruby(block: CustomView.() -> Unit = {}) = CustomView(this, "ruby").visit(page, block)

fun View.s(block: CustomView.() -> Unit = {}) = CustomView(this, "s").visit(page, block)

fun View.samp(block: CustomView.() -> Unit = {}) = CustomView(this, "samp").visit(page, block)

fun View.script(block: CustomView.() -> Unit = {}) = CustomView(this, "script").visit(page, block)

fun View.section(block: CustomView.() -> Unit = {}) = CustomView(this, "section").visit(page, block)

fun View.select(block: CustomView.() -> Unit = {}) = CustomView(this, "select").visit(page, block)

fun View.small(block: CustomView.() -> Unit = {}) = CustomView(this, "small").visit(page, block)

fun View.source(block: CustomView.() -> Unit = {}) = CustomView(this, "source").visit(page, block)

fun View.span(block: CustomView.() -> Unit = {}) = CustomView(this, "span").visit(page, block)

fun View.strong(block: CustomView.() -> Unit = {}) = CustomView(this, "strong").visit(page, block)

fun View.style(block: CustomView.() -> Unit = {}) = CustomView(this, "style").visit(page, block)

fun View.sub(block: CustomView.() -> Unit = {}) = CustomView(this, "sub").visit(page, block)

fun View.summary(block: CustomView.() -> Unit = {}) = CustomView(this, "summary").visit(page, block)

fun View.sup(block: CustomView.() -> Unit = {}) = CustomView(this, "sup").visit(page, block)

fun View.svg(block: CustomView.() -> Unit = {}) = CustomView(this, "svg").visit(page, block)

fun View.tbody(block: CustomView.() -> Unit = {}) = CustomView(this, "tbody").visit(page, block)

fun View.td(block: CustomView.() -> Unit = {}) = CustomView(this, "td").visit(page, block)

fun View.template(block: CustomView.() -> Unit = {}) = CustomView(this, "template").visit(page, block)

fun View.textarea(block: CustomView.() -> Unit = {}) = CustomView(this, "textarea").visit(page, block)

fun View.time(block: CustomView.() -> Unit = {}) = CustomView(this, "time").visit(page, block)

fun View.title(block: CustomView.() -> Unit = {}) = CustomView(this, "title").visit(page, block)

fun View.tr(block: CustomView.() -> Unit = {}) = CustomView(this, "tr").visit(page, block)

fun View.track(block: CustomView.() -> Unit = {}) = CustomView(this, "track").visit(page, block)

fun View.u(block: CustomView.() -> Unit = {}) = CustomView(this, "u").visit(page, block)

fun View.varElement(block: CustomView.() -> Unit = {}) = CustomView(this, "var").visit(page, block)

fun View.video(block: CustomView.() -> Unit = {}) = CustomView(this, "video").visit(page, block)

fun View.wbr(block: CustomView.() -> Unit = {}) = CustomView(this, "wbr").visit(page, block)


infix fun <V : View> V.withHorizontalLayout(block: V.() -> Unit): V {
    withHorizontalLayout()
    block()
    return this
}

infix fun <V : View> V.withVerticalLayout(block: V.() -> Unit): V {
    withVerticalLayout()
    block()
    return this
}

fun View.withHorizontalLayout() = withLinearLayout(LinearLayoutOrientation.Horizontal)
fun View.withVerticalLayout() = withLinearLayout(LinearLayoutOrientation.Vertical)

private fun View.withLinearLayout(orientation: LinearLayoutOrientation) {
    if (orientation == LinearLayoutOrientation.Horizontal) {
        addRuleSet(page.horizontalLayoutClass)
    } else {
        addRuleSet(page.verticalLayoutClass)
    }
    addRuleSet(page.linearLayoutClass)
}

infix fun <V : View> V.withGrid(block: V.() -> Unit): V = apply {
    withGrid()
    block()
}

fun View.withGrid() {
    addRuleSet(page.gridLayoutClass)
}