@file:Suppress("unused")

package com.narbase.kunafa.core.components


import com.narbase.kunafa.core.dimensions.LinearDimension
import com.narbase.kunafa.core.drawable.Color
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