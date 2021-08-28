@file:Suppress("unused")

package com.narbase.kunafa.core.components


import com.narbase.kunafa.core.dimensions.LinearDimension
import com.narbase.kunafa.core.drawable.Color
import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class TextInput(parent: View? = null) : View(parent) {

    override val element: HTMLInputElement = document.createElement("input") as HTMLInputElement

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

    var type
        get() = element.type
        set(value) {
            element.type = value
        }


    var textSize: LinearDimension? = null
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

    var onChange: ((Event) -> Unit)?
        get() = element.onchange
        set(value) {
            element.onchange = value
        }

}