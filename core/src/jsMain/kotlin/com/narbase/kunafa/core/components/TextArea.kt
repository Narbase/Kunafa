package com.narbase.kunafa.core.components

import org.w3c.dom.HTMLTextAreaElement

import kotlinx.browser.document


actual class TextArea(parent: View? = null) : View(parent) {

    override val element: HTMLTextAreaElement = document.createElement("textarea") as HTMLTextAreaElement

    var text
        get() = element.value
        set(value) {
            element.value = value
        }

    var placeholder
        get() = element.placeholder
        set(value) {
            element.placeholder = value
        }
}

