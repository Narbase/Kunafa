package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.DependentDimension
import net.avatarapps.kunafa.core.dimensions.Dimension
import net.avatarapps.kunafa.core.dimensions.DimensionNotCalculatedException
import net.avatarapps.kunafa.core.dimensions.DynamicDimension
import org.w3c.dom.HTMLElement

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */

internal class WrapContent: DynamicDimension(){
    override fun configure(element: HTMLElement, type: Dimension.Type) {
        when (type) {
            Type.height -> {
                element.style.height = "auto"
//                element.style.overflowY = "auto"
            }
            Type.width -> {
                element.style.width = "auto"
//                element.style.overflowX = "auto"
            }
        }
    }

}

val View.wrapContent: DynamicDimension
    get() {
        return WrapContent()
    }

