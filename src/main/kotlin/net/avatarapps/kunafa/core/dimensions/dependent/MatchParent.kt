package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.DependentDimension
import net.avatarapps.kunafa.core.dimensions.DependentDimension.Dependency.parent
import net.avatarapps.kunafa.core.dimensions.DependentDimension.Type.height
import net.avatarapps.kunafa.core.dimensions.DependentDimension.Type.width
import net.avatarapps.kunafa.core.dimensions.DimensionNotCalculatedException
import net.avatarapps.kunafa.core.dimensions.IndependentDimension

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/6/17.
 */

//class MatchParent internal constructor(val view: View) : DependentDimension() {
//    override val dependency = parent
//    override fun calculate() {
//        val parentDimension = when (type) {
//            width -> getParentContentWidth()
//            height -> getParentContentHeight()
//            null -> throw DimensionNotCalculatedException("${view.id}.height")
//        }
//        calculatedDimension = parentDimension
//        isCalculated = true
//    }
//
//    private fun getParentContentWidth(): IndependentDimension {
//        return view.parent?.contentWidth ?:
//                throw DimensionNotCalculatedException("${view.parent?.id}.width")
//    }
//
//    private fun getParentContentHeight(): IndependentDimension {
//        return view.parent?.contentHeight ?:
//                throw DimensionNotCalculatedException("${view.parent?.id}.height")
//    }
//}
class MatchParent internal constructor(val view: View) : DependentDimension() {
    override fun setListeners() {
        when (type) {
            width -> {
                val updatePixels = {
                    val parentContent = view.parent?.contentWidth?.pixels ?: 0
                    pixels = (parentContent - (view.marginStart.pixels + view.marginEnd.pixels)).takeIf { it > 0 } ?: 0
                }
                view.addOnParentResizedListener(view, updatePixels)
                updatePixels()
            }
            height -> {
                val updatePixels = {
                    val parentContent = view.parent?.contentHeight?.pixels ?: 0
                    pixels = (parentContent - (view.marginTop.pixels + view.marginBottom.pixels)).takeIf { it > 0 } ?: 0
                }
                view.addOnParentResizedListener(view, updatePixels)
                updatePixels()
            }
            null -> throw DimensionNotCalculatedException("${view.id}.type")
        }
    }

    override val dependency = parent
    override fun calculate() {
    }
}

val View.matchParent: MatchParent
    get() {
        return MatchParent(this)
    }


