package com.narbase.kunafa.core.components

import org.w3c.dom.HTMLTextAreaElement

import kotlinx.browser.document
import org.w3c.dom.HTMLSpanElement


actual class Span(parent: View? = null) : View(parent) {

    override val element: HTMLSpanElement = document.createElement("span") as HTMLSpanElement

    var text
        get() = element.innerText
        set(value) {
            element.innerText = value
        }

}

