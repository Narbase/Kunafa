@file:Suppress("unused")

package com.narbase.kunafa.core.components


import org.w3c.dom.*
import kotlin.browser.document


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class Table(parent: View? = null) : View(parent) {
    override val element: HTMLTableElement = (document.createElement("table") as HTMLTableElement)
}

class TableRow(parent: View? = null) : View(parent) {
    override val element: HTMLTableRowElement = (document.createElement("tr") as HTMLTableRowElement)
}

class TableCell(parent: View? = null) : View(parent) {

    override val element: HTMLTableCellElement = (document.createElement("td") as HTMLTableCellElement)
    var text
        get() = element.innerHTML
        set(value) {
            element.innerHTML = value
        }
}

class TableHeaderCell(parent: View? = null) : View(parent) {
    override val element: HTMLElement = (document.createElement("th") as HTMLTableCellElement)

    var text
        get() = element.innerHTML
        set(value) {
            element.innerHTML = value
        }
}

class TableHeader(parent: View? = null) : View(parent) {
    override val element: HTMLElement = (document.createElement("thead") as HTMLTableSectionElement)
}

class TableFooter(parent: View? = null) : View(parent) {
    override val element: HTMLElement = (document.createElement("tfoot") as HTMLTableSectionElement)
}

class TableBody(parent: View? = null) : View(parent) {
    override val element: HTMLElement = (document.createElement("tbody") as HTMLTableSectionElement)
}
