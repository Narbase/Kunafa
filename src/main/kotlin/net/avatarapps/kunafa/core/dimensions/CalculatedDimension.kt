package net.avatarapps.kunafa.core.dimensions

import net.avatarapps.kunafa.core.dimensions.independent.Pixel
import net.avatarapps.kunafa.core.dimensions.independent.px
import org.w3c.dom.HTMLElement

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

abstract class Dimension {
    enum class Type {
        width,
        height,
    }
}

abstract class CalculatedDimension : Dimension() {
    abstract var pixels: Int
}

operator fun <D : IndependentDimension> D.minus(dimension: IndependentDimension): Pixel {
    return (this.pixels - dimension.pixels).px
}

operator fun <D : IndependentDimension> D.plus(dimension: IndependentDimension): Pixel {
    return (this.pixels + dimension.pixels).px
}

abstract class DependentDimension : CalculatedDimension() {
    var type: Type? = null
    abstract fun setListeners()
    var onChange: (() -> Unit)? = null
    override var pixels = 0
        set(value) {
            field = value
            onChange?.invoke()
        }


    enum class Dependency {
        parent,
        children
    }
}

abstract class DynamicDimension : Dimension(){
    abstract fun configure(element: HTMLElement, type: Dimension.Type)
}

class ChangingPixelsOfDependentDimensionError : Exception()

class DimensionNotCalculatedException(msg: String = "") : Exception(msg)

abstract class IndependentDimension : CalculatedDimension() {
//    override var isCalculated = true
}
