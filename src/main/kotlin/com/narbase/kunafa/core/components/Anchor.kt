@file:Suppress("unused")

package com.narbase.kunafa.core.components

import org.w3c.dom.HTMLAnchorElement
import kotlin.browser.document

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/03/08.
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
