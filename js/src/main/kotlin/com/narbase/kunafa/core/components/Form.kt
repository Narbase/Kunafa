@file:Suppress("unused")

package com.narbase.kunafa.core.components


import kotlinx.browser.document
import org.w3c.dom.HTMLFieldSetElement
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLLegendElement

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

class Form(parent: View? = null) : View(parent) {
    override val element: HTMLFormElement = (document.createElement("form") as HTMLFormElement)
}


class FieldSet(parent: View? = null) : View(parent) {
    override val element: HTMLFieldSetElement = (document.createElement("fieldset") as HTMLFieldSetElement)
}

class Legend(parent: View? = null) : View(parent) {

    override val element: HTMLLegendElement = (document.createElement("legend") as HTMLLegendElement)
    var text
        get() = element.innerHTML
        set(value) {
            element.innerHTML = value
        }
}
