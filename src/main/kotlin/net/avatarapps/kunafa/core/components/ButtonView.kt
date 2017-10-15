package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLSpanElement
import kotlin.browser.document

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/15/17.
 */
class ButtonView(parent: Container? = null) : View(parent) {
    val button by lazy {
        val b = document.createElement("button") as HTMLButtonElement
        element.append(b)
        return@lazy b
    }

    override fun updateElementHeight() {
        super.updateElementHeight()
        contentHeight?.let {
            button.style.height = "${it.pixels}px"
        }
    }

    override fun updateElementWidth() {
        super.updateElementWidth()
        contentWidth?.let {
            button.style.width = "${it.pixels}px"
        }
    }

}