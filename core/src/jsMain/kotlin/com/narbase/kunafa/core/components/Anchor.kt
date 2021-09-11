@file:Suppress("unused")

package com.narbase.kunafa.core.components

import kotlinx.browser.document
import org.w3c.dom.HTMLAnchorElement

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
actual class Anchor(
        parent: View? = null,
        override val element: HTMLAnchorElement = (document.createElement("a") as HTMLAnchorElement)
) : View(parent) {

    var text
        get() = element.innerHTML
        set(value) {
            element.innerHTML = value
        }

    var href
        get() = element.href
        set(value) {
            element.href = value
        }
}
