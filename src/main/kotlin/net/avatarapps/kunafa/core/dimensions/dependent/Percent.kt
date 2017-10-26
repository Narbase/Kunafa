package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.DependentDimension
import net.avatarapps.kunafa.core.dimensions.DimensionNotCalculatedException

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/19/17.
 */
class Weight internal constructor(val view: View?, val percentage: Int) : DependentDimension() {
    override fun setListeners() {
//        when (type) {
//            Type.width -> {
//                val updatePixels = {
//                    val content = view?.parent?.contentWidth?.pixels ?: 0
//                    pixels = (content - (view.marginStart.pixels + view.marginEnd.pixels)).takeIf { it > 0 } ?: 0
//                }
//                view.addOnParentResizedListener(view, updatePixels)
//                updatePixels()
//            }
//            Type.height -> {
//                val updatePixels = {
//                    val content = view.parent?.contentHeight?.pixels ?: 0
//                    pixels = (content - (view.marginTop.pixels + view.marginBottom.pixels)).takeIf { it > 0 } ?: 0
//                }
//                view.addOnParentResizedListener(view, updatePixels)
//                updatePixels()
//            }
//            null -> throw DimensionNotCalculatedException("${view.id}.type")
//        }
    }
}

infix fun View.weightOf(percentage: Int): Weight {
    return Weight(this, percentage)
}
