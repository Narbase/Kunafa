package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.DependentDimension
import net.avatarapps.kunafa.core.dimensions.DimensionNotCalculatedException
import net.avatarapps.kunafa.core.dimensions.DynamicDimension

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */
open class WrapContent internal constructor(val view: View) : DependentDimension() {
    override fun setListeners() {
        when (type) {
            Type.width -> {
                view.addOnChildrenResizedListener(view){
                    println("WrapContent Resized")
                    pixels = view.wrappedContentWidth.pixels
                }
                pixels = view.wrappedContentWidth.pixels
            }
            Type.height -> {
                view.addOnChildrenResizedListener(view){
                    pixels = view.wrappedContentHeight.pixels
                }
                pixels = view.wrappedContentHeight.pixels
            }
            null -> throw CalculatedDimensionTypeNotDefinedError("${view.id}.type")
        }
    }
}

open class ParentDependentWrapContent(view: View) : WrapContent(view) {
    override fun setListeners() {
        when (type) {
            Type.width -> {
                view.addOnResizedListener(view){
                    pixels = view.wrappedContentWidth.pixels
                }
                pixels = view.wrappedContentWidth.pixels
            }
            Type.height -> {
                view.addOnResizedListener(view){
                    pixels = view.wrappedContentHeight.pixels
                }
                pixels = view.wrappedContentHeight.pixels
            }
            null -> throw CalculatedDimensionTypeNotDefinedError("${view.id}.type")
        }
    }
}

class CalculatedDimensionTypeNotDefinedError(msg: String) : Exception(msg)

val View.wrapContent: DynamicDimension
    get() {
        return DynamicDimension("auto")
    }

val TextView.wrapContent: DynamicDimension
    get() {
        return DynamicDimension("auto")
    }
