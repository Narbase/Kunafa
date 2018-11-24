@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Container
import com.narbase.kunafa.core.dimensions.IndependentDimension
import com.narbase.kunafa.core.drawable.Color
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.browser.document

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/31/17.
 */
class TextInput (parent: Container? = null) : View(parent) {

    override val element: HTMLElement = document.createElement("input") as HTMLInputElement

    var text
        get()= (element as HTMLInputElement).value
        set(value) {
            (element as HTMLInputElement).value = value
        }

    var placeholder = ""
        set(value) {
            field = value
            (element as HTMLInputElement).placeholder = value
        }

    var type = ""
        set(value) {
            field = value
            (element as HTMLInputElement).type = value
        }

    var textAlign: TextView.TextAlign? = null
        set(value) {
            field = value
            value?.let {
                element.style.textAlign = it.cssName
            }
        }

    var textSize: IndependentDimension? = null
        set(value) {
            field = value
            value?.let {
                element.style.fontSize = it.toString()
            }
        }

    var textColor: Color = Color()
        set(value) {
            field = value
            element.style.color = value.toCss()
        }

    var onChange: ((Event) -> Unit)? = null
        set(value) {
            field = value
            (element as HTMLInputElement).onchange = value
        }

}