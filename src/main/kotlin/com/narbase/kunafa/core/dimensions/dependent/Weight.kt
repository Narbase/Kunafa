@file:Suppress("unused")

package com.narbase.kunafa.core.dimensions.dependent

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.dimensions.DynamicDimension
import org.w3c.dom.HTMLElement

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
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
