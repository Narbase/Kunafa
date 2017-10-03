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
class WrapContent internal constructor(val container: Container): CalculatedDimension(container) {
    override val dependsOnParent: Boolean = false
    override var pixels: Int
        get() = when(type){
            CalculatedDimension.Type.width -> container.wrappedContentWidth.pixels
            CalculatedDimension.Type.height -> container.wrappedContentHeight.pixels
            null -> throw CalculatedDimensionTypeNotDefinedError()
        }
        set(value) {
            throw ChangingPixelsOfCalculatedDimensionError()
        }
}

class ChangingPixelsOfCalculatedDimensionError : Exception()

class CalculatedDimensionTypeNotDefinedError : Exception()

val Container.wrapContent: WrapContent
    get() {
        return WrapContent(this)
    }
