package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.DependentDimension

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */
class WrapContent internal constructor(val view: View) : DependentDimension() {
    override val dependency = Dependency.children
    override fun calculate() {
        calculatedDimension = when (type) {
            Type.width -> view.wrappedContentWidth
            Type.height -> view.wrappedContentHeight
            null -> throw CalculatedDimensionTypeNotDefinedError()
        }
        isCalculated = true
    }
}


class CalculatedDimensionTypeNotDefinedError : Exception()

val View.wrapContent: WrapContent
    get() {
        return WrapContent(this)
    }
