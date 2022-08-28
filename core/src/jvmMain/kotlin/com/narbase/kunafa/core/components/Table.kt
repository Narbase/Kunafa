@file:Suppress("unused")

package com.narbase.kunafa.core.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
actual class Table(parent: View? = null) : View(parent) {
    override val element = "table"
}

actual class TableRow(parent: View? = null) : View(parent) {
    override val element = "tr"
}

actual class TableCell(parent: View? = null) : View(parent) {
    override val element = "td"
}

actual class TableHeaderCell(parent: View? = null) : View(parent) {
    override val element = "th"
}

actual class TableHeader(parent: View? = null) : View(parent) {
    override val element = "thead"
}

actual class TableFooter(parent: View? = null) : View(parent) {
    override val element = "tfoot"
}

actual class TableBody(parent: View? = null) : View(parent) {
    override val element = "tbody"
}
