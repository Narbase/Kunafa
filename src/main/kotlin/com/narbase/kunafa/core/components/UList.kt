@file:Suppress("unused")

package com.narbase.kunafa.core.components


import org.w3c.dom.HTMLLIElement
import org.w3c.dom.HTMLUListElement
import kotlin.browser.document

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2018] Narbase Technologies
 * All Rights Reserved.
 * Created by ${user}
 * On: ${date}.
 */

open class UList(parent: View? = null) : HtmlView(parent) {
    override val element: HTMLUListElement = document.createElement("ul") as HTMLUListElement
}

open class ListItem(parent: View? = null) : HtmlView(parent) {
    override val element: HTMLLIElement = document.createElement("li") as HTMLLIElement
}
