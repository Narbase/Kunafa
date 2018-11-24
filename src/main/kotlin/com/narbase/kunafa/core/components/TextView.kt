@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Container
import com.narbase.kunafa.core.dimensions.LinearDimension
import com.narbase.kunafa.core.drawable.Color

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/9/17.
 */
open class TextView(parent: Container? = null) : View(parent) {

    var text = ""
        set(value) {
            field = value
            element.innerHTML = value
        }

    var textAlign: TextAlign? = null
        set(value) {
            field = value
            value?.let {
                element.style.textAlign = it.cssName
            }
        }

    var textSize: LinearDimension? = null
        set(value) {
            field = value
            value?.let {
                element.style.fontSize = it.toString()
            }
        }

    enum class TextAlign(val cssName: String) {
        Left("left"),
        Right("right"),
        Center("center"),
        Justify("justify"),
        Initial("initial"),
        Inherit("inherit")
    }

    var textColor: Color = Color()
        set(value) {
            field = value
            element.style.color = value.toCss()
        }
}