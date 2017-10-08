package net.avatarapps.kunafa.core.dimensions

import net.avatarapps.kunafa.core.components.View

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/6/17.
 */

class MatchParent internal constructor(val view: View) : DependentDimension() {
    override val dependency = Dependency.parent
    override fun calculate() {
        val parentDimension = when (type) {
            DependentDimension.Type.width -> getParentContentWidth()
            DependentDimension.Type.height -> getParentContentHeight()
            null -> throw DimensionNotCalculatedException("${view.id}.height")
        }
        calculatedDimension = parentDimension
        isCalculated = true
    }

    private fun getParentContentWidth(): IndependentDimension {
        return view.parent?.contentWidth ?:
                throw DimensionNotCalculatedException("${view.parent?.id}.width")
    }

    private fun getParentContentHeight(): IndependentDimension {
        return view.parent?.contentHeight ?:
                throw DimensionNotCalculatedException("${view.parent?.id}.height")
    }
}

val View.matchParent: MatchParent
    get() {
        return MatchParent(this)
    }


