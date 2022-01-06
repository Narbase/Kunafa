@file:Suppress("unused")

package com.narbase.kunafa.core.components

import kotlinx.browser.document
import org.w3c.dom.HTMLElement


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
actual open class TextView(
    parent: View? = null,
    element: HTMLElement = document.createElement("div") as HTMLElement
) : View(parent, element) {

    //todo: move text and text builder to view
    var text
        get() = element.innerText
        set(value) {
            element.innerText = value
        }
}