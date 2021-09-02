@file:Suppress("unused")

package com.narbase.kunafa.core.ssr.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class Table(parent: View? = null) : View(parent) {
    override val element = "table"
}

class TableRow(parent: View? = null) : View(parent) {
    override val element = "tr"
}

class TableCell(parent: View? = null) : View(parent) {
    override val element = "td"
}

class TableHeaderCell(parent: View? = null) : View(parent) {
    override val element = "th"
}

class TableHeader(parent: View? = null) : View(parent) {
    override val element = "thead"
}

class TableFooter(parent: View? = null) : View(parent) {
    override val element = "tfoot"
}

class TableBody(parent: View? = null) : View(parent) {
    override val element = "tbody"
}
