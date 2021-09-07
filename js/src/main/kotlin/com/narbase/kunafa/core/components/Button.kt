package com.narbase.kunafa.core.components


import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
@Suppress("unused")
class Button(
        parent: View? = null,
        override val element: HTMLButtonElement = document.createElement("button") as HTMLButtonElement
) : View(parent) {


    var text: String?
        get() = element.textContent
        set(value) {
            element.textContent = value
        }
}