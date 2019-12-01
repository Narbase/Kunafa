@file:Suppress("unused")

package com.narbase.kunafa.core.components


import org.w3c.dom.HTMLFieldSetElement
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLLegendElement
import kotlin.browser.document

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2018] Narbase Technologies
 * All Rights Reserved.
 * Created by ${user}
 * On: ${date}.
 */

class Form(parent: View? = null) : HtmlView(parent) {
    override val element: HTMLFormElement = (document.createElement("form") as HTMLFormElement)
}


class FieldSet(parent: View? = null) : HtmlView(parent) {
    override val element: HTMLFieldSetElement = (document.createElement("fieldset") as HTMLFieldSetElement)
}

class Legend(parent: View? = null) : HtmlView(parent) {

    override val element: HTMLLegendElement = (document.createElement("legend") as HTMLLegendElement)
    var text
        get() = element.innerHTML
        set(value) {
            element.innerHTML = value
        }
}
