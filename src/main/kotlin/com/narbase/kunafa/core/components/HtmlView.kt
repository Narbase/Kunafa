@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.components

import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent
import kotlin.browser.document

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
open class HtmlView(parent: View? = null) : View(parent) {


    override val element: HTMLElement = document.createElement("div") as HTMLDivElement


    open var onClick: ((MouseEvent) -> dynamic)?
        get() = element.onclick
        set(value) {
            element.onclick = value
        }


}
