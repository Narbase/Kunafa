@file:Suppress("unused")

package com.narbase.kunafa.core.components

import org.w3c.dom.HTMLAnchorElement
import kotlin.browser.document

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class Anchor(parent: View? = null) : View(parent) {
    override val element: HTMLAnchorElement = (document.createElement("a") as HTMLAnchorElement)
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
