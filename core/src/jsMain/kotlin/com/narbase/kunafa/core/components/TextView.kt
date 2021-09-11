@file:Suppress("unused")

package com.narbase.kunafa.core.components

import kotlinx.browser.document
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
actual open class TextView(parent: View? = null, override val element: HTMLElement = document.createElement("div") as HTMLDivElement) : View(parent) {

    var text
        get() = element.innerText
        set(value) {
            element.innerText = value
        }
}