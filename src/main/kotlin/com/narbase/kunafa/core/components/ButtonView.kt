package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Container
import com.narbase.kunafa.core.dimensions.DynamicDimension
import com.narbase.kunafa.core.dimensions.LinearDimension
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.events.Event
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
//    override val element: HTMLElement = document.createElement("button") as HTMLButtonElement

    val button by lazy {
        val b = document.createElement("button") as HTMLButtonElement
        element.append(b)
        return@lazy b
    }

    override var onClick: ((Event) -> Unit)? = null
        set(value) {
            field = value
            button.onclick = onClick
        }

    override fun updateContentWidth() {
        super.updateContentWidth()
        if (width is DynamicDimension) {
//            (width as? DynamicDimension)?.configure(button, Dimension.Type.width)
        } else (width as? LinearDimension)?.let {
            button.style.width = it.toString()
            button.style.minWidth = it.toString()
        }
    }

    override fun updateContentHeight() {
        super.updateContentHeight()
        if (height is DynamicDimension) {
//            (height as? DynamicDimension)?.configure(button, Dimension.Type.height)
        } else (height as? LinearDimension)?.let {
            button.style.height = it.toString()
            button.style.minHeight = it.toString()
        }
    }

}