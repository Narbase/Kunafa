@file:Suppress("unused")

package com.narbase.kunafa.core.components


import kotlinx.browser.document
import org.w3c.dom.HTMLFieldSetElement
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLLegendElement

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

class Form(parent: View? = null, override val element: HTMLFormElement = (document.createElement("form") as HTMLFormElement)) : View(parent)

class FieldSet(parent: View? = null, override val element: HTMLFieldSetElement = (document.createElement("fieldset") as HTMLFieldSetElement)) : View(parent)

class Legend(parent: View? = null, override val element: HTMLLegendElement = (document.createElement("legend") as HTMLLegendElement)) : View(parent) {

    var text
        get() = element.innerHTML
        set(value) {
            element.innerHTML = value
        }
}
