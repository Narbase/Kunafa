package net.avatarapps.kunafa.core.dimensions

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

abstract class Dimension {
    abstract var pixels: Int
    open var isCalculated = true
}

operator fun <D : IndependentDimension> D.minus(dimension: IndependentDimension): Pixel {
    return (this.pixels - dimension.pixels).px
}

operator fun <D : IndependentDimension> D.plus(dimension: IndependentDimension): Pixel {
    return (this.pixels + dimension.pixels).px
}

abstract class DependentDimension : Dimension() {
    var type: Type? = null
    abstract val dependency: Dependency
    override var isCalculated = false
    var calculatedDimension: IndependentDimension? = null
    override var pixels
        get() = calculatedDimension?.pixels ?: throw DimensionNotCalculatedException()
        set(value) {
            throw ChangingPixelsOfDependentDimensionError()
        }

    abstract fun calculate()

    enum class Type {
        width,
        height,
    }

    enum class Dependency {
        parent,
        children
    }
}

class ChangingPixelsOfDependentDimensionError : Exception()

class DimensionNotCalculatedException(msg: String = "") : Exception(msg)

abstract class IndependentDimension : Dimension() {
    override var isCalculated = true
}
