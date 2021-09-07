@file:Suppress("unused")

package com.narbase.kunafa.core.components


import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class Radio(
        parent: View? = null,
        override val element: HTMLInputElement = (document.createElement("input") as HTMLInputElement).apply {
            this.type = "radio"
        }
) : View(parent) {

    var isChecked: Boolean
        get() = element.checked
        set(value) {
            element.checked = value

        }

    var onChange: ((Event) -> Unit)?
        get() = element.onchange
        set(value) {
            element.onchange = value
        }

}

