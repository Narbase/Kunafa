package net.avatarapps.kunafa.core.dimensions

import net.avatarapps.kunafa.core.components.layout.Container

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */
class WrapContent internal constructor(val container: Container) : DependentDimension() {
    override val dependency = Dependency.children
    override fun calculate() {
        calculatedDimension = when (type) {
            DependentDimension.Type.width -> container.wrappedContentWidth
            DependentDimension.Type.height -> container.wrappedContentHeight
            null -> throw CalculatedDimensionTypeNotDefinedError()
        }
        isCalculated = true
    }
}


class CalculatedDimensionTypeNotDefinedError : Exception()

val Container.wrapContent: WrapContent
    get() {
        return WrapContent(this)
    }
