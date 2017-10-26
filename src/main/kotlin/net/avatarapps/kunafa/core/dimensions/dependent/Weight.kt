package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.DynamicDimension
import org.w3c.dom.HTMLElement

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/19/17.
 */
class Weight internal constructor(private val value: Int) : DynamicDimension() {
    override fun configure(element: HTMLElement, type: Type) {
        when (type) {
            Type.height -> {
                element.style.height = "auto"
                element.style.flex = "$value 0 0px"
            }
            Type.width -> {
                element.style.width = "auto"
                element.style.flex = "$value 0 0px"
            }
        }
    }
}

infix fun View.weightOf(value: Int): Weight {
    return Weight(value)
}
