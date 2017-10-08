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
            DependentDimension.Type.width -> getParentWidth()
            DependentDimension.Type.height -> getParentHeight()
            null -> throw DimensionNotCalculatedException("${view.id}.height")
        }

        var vr = parentDimension.pixels -
                if (type == Type.width) ((view.parent?.paddingStart?.pixels ?: 0) + (view.parent?.paddingEnd?.pixels ?: 0))
                else ((view.parent?.paddingStart?.pixels ?: 0) + (view.parent?.paddingEnd?.pixels ?: 0))

        calculatedDimension = parentDimension
        isCalculated = true
    }

    private fun getParentWidth(): IndependentDimension {
        (view.parent?.width as? IndependentDimension)?.let {
            return it
        }
        (view.parent?.width as? DependentDimension)?.let {
            if (it.isCalculated)
                it.calculatedDimension?.let {
                    return it
                }
        }
        throw DimensionNotCalculatedException("${view.parent?.id}.width")

    }

    private fun getParentHeight(): IndependentDimension {

        (view.parent?.height as? IndependentDimension)?.let {
            return it
        }
        (view.parent?.height as? DependentDimension)?.let {
            if (it.isCalculated)
                it.calculatedDimension?.let {
                    return it
                }
        }
        throw DimensionNotCalculatedException("${view.parent?.id}.height")
    }
}

val View.matchParent: MatchParent
    get() {
        return MatchParent(this)
    }


