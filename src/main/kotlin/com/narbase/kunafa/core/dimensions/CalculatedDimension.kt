package com.narbase.kunafa.core.dimensions

import com.narbase.kunafa.core.dimensions.independent.Pixel
import com.narbase.kunafa.core.dimensions.independent.px
import org.w3c.dom.HTMLElement

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
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


abstract class DynamicDimension : Dimension(){
    abstract fun configure(element: HTMLElement, type: Dimension.Type)
}


abstract class IndependentDimension : CalculatedDimension() {
//    override var isCalculated = true
}
