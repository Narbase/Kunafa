package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.Dimension
import net.avatarapps.kunafa.core.dimensions.DynamicDimension
import org.w3c.dom.HTMLElement
import org.w3c.xhr.XMLHttpRequest

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */

internal class WrapContent: DynamicDimension(){
    override fun configure(element: HTMLElement, type: Dimension.Type) {
        when (type) {
            Type.height -> {
                element.style.height = "auto"
            }
            Type.width -> {
                element.style.width = "auto"
            }
        }
    }
}

val View.wrapContent: DynamicDimension
    get() {
        return WrapContent()
    }

