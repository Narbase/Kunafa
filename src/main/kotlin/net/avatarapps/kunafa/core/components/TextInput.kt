package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.dimensions.CalculatedDimension
import net.avatarapps.kunafa.core.dimensions.Dimension
import net.avatarapps.kunafa.core.dimensions.DynamicDimension
import net.avatarapps.kunafa.core.dimensions.IndependentDimension
import net.avatarapps.kunafa.core.drawable.Color
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSpanElement
import org.w3c.dom.events.Event
import kotlin.browser.document

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
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
                element.style.fontSize = "${it.pixels}px"
            }
        }

    var textColor: Color = Color()
        set(value) {
            field = value
            element.style.color = "rgba(${value.red},${value.green},${value.blue},${value.alpha})"
        }

    var onChange: ((Event) -> Unit)? = null
    set(value) {
        field = value
        (element as HTMLInputElement).onchange = value
    }

}