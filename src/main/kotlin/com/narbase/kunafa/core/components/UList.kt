@file:Suppress("unused")

package com.narbase.kunafa.core.components


import org.w3c.dom.HTMLLIElement
import org.w3c.dom.HTMLUListElement
import kotlin.browser.document

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

open class UList(parent: View? = null) : View(parent) {
    override val element: HTMLUListElement = document.createElement("ul") as HTMLUListElement
}

open class ListItem(parent: View? = null) : View(parent) {
    override val element: HTMLLIElement = document.createElement("li") as HTMLLIElement
}
