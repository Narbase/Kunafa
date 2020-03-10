package com.narbase.kunafa.core.components


import org.w3c.dom.HTMLButtonElement
import kotlin.browser.document

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
@Suppress("unused")
class Button(parent: View? = null) : View(parent) {
    override val element: HTMLButtonElement = document.createElement("button") as HTMLButtonElement
    var text: String?
        get() = element.textContent
        set(value) {
            element.textContent = value
        }
}