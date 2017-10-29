package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.dimensions.IndependentDimension
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color
import org.w3c.dom.HTMLSpanElement
import kotlin.browser.document
import kotlin.browser.window

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/9/17.
 */
open class TextView(parent: Container? = null) : View(parent) {
    val span by lazy {
        val s = document.createElement("span") as HTMLSpanElement
        element.append(s)
        return@lazy s
    }

    var text = ""
        set(value) {
            field = value
            span.innerHTML = value
        }

    var textAlign: TextAlign? = null
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


    override val wrappedContentHeight: IndependentDimension
        get() {
            return span.offsetHeight.px
        }

    override val wrappedContentWidth: IndependentDimension
        get() = span.offsetWidth.px

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
        element.style.color = "rgba(${value.red},${value.green},${value.blue},${value.alpha})"
    }
}