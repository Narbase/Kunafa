package com.narbase.kunafa.core.components

import kotlinx.browser.document
import org.w3c.dom.HTMLElement

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
@Suppress("unused")
class CustomView(
        parent: View? = null,
        override val element: HTMLElement
) : View(parent) {
    constructor(
            parent: View? = null,
            elementName: String,
    ) : this(parent, document.createElement(elementName) as HTMLElement)
}