package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Container
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import kotlin.browser.document

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/15/17.
 */
class ButtonView(parent: Container? = null) : View(parent) {
    override val element: HTMLElement = document.createElement("button") as HTMLButtonElement
    var text: String?
        get() = element.textContent
        set(value) {
            element.textContent = value
        }
}