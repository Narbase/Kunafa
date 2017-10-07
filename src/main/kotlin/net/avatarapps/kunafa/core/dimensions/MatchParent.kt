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

class MatchParent internal constructor(val view: View): DependentDimension() {
    override val dependency = Dependency.parent
    override fun calculate() {
        calculatedDimension = when(type){
            DependentDimension.Type.width -> {
                if (view.parent?.width?.isCalculated?: false == false)
                    throw DimensionNotCalculatedException("${view.parent?.id}.width")

                if (view.parent?.width is IndependentDimension) view.parent?.width as IndependentDimension
                else (view.parent?.width as? DependentDimension)?.calculatedDimension
                        ?: throw DimensionNotCalculatedException("${view.parent?.id}.width")
            }
            DependentDimension.Type.height -> {
                if (view.parent?.height?.isCalculated?: false == false)
                    throw DimensionNotCalculatedException("${view.parent?.id}.height")

                if (view.parent?.height is IndependentDimension) view.parent?.height as IndependentDimension
                else (view.parent?.height as? DependentDimension)?.calculatedDimension
                        ?: throw DimensionNotCalculatedException("${view.parent?.id}.height")
            }
            null -> throw DimensionNotCalculatedException("${view.id}.height")
        }
        isCalculated = true
    }
}

val View.matchParent: MatchParent
    get() {
        return MatchParent(this)
    }


