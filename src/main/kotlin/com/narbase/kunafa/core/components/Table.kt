@file:Suppress("unused")

package com.narbase.kunafa.core.components


import org.w3c.dom.*
import kotlin.browser.document


/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/31/17.
 */
class Table(parent: BaseElement? = null) : View(parent) {
    override val element: HTMLTableElement = (document.createElement("table") as HTMLTableElement)
}

class TableRow(parent: BaseElement? = null) : View(parent) {
    override val element: HTMLTableRowElement = (document.createElement("tr") as HTMLTableRowElement)
}

class TableCell(parent: BaseElement? = null) : View(parent) {

    override val element: HTMLTableCellElement = (document.createElement("td") as HTMLTableCellElement)
    var text
        get() = element.innerHTML
        set(value) {
            element.innerHTML = value
        }
}

class TableHeaderCell(parent: BaseElement? = null) : View(parent) {
    override val element: HTMLElement = (document.createElement("th") as HTMLTableCellElement)

    var text
        get() = element.innerHTML
        set(value) {
            element.innerHTML = value
        }
}

class TableHeader(parent: BaseElement? = null) : View(parent) {
    override val element: HTMLElement = (document.createElement("thead") as HTMLTableSectionElement)
}

class TableFooter(parent: BaseElement? = null) : View(parent) {
    override val element: HTMLElement = (document.createElement("tfoot") as HTMLTableSectionElement)
}

class TableBody(parent: BaseElement? = null) : View(parent) {
    override val element: HTMLElement = (document.createElement("tbody") as HTMLTableSectionElement)
}
