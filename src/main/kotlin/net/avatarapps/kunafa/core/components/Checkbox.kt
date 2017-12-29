package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.dimensions.IndependentDimension
import net.avatarapps.kunafa.core.drawable.Color
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
class Checkbox(parent: Container? = null) : View(parent) {

    override val element: HTMLElement = (document.createElement("input") as HTMLInputElement).apply {
        this.type = "checkbox"
    }

    var isChecked: Boolean
        get() = (element as HTMLInputElement).checked
        set(value) {
            (element as HTMLInputElement).checked = value

        }

    var onChange: ((Event) -> Unit)? = null
        set(value) {
            field = value
            (element as HTMLInputElement).onchange = value
        }

}