package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.DependentDimension
import net.avatarapps.kunafa.core.dimensions.DimensionNotCalculatedException

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
            null -> throw DimensionNotCalculatedException("${view.id}.type")
        }

    }

    override val dependency = Dependency.children
    override fun calculate() {
//        calculatedDimension = when (type) {
//            Type.width -> view.wrappedContentWidth
//            Type.height -> view.wrappedContentHeight
//            null -> throw CalculatedDimensionTypeNotDefinedError()
//        }
//        isCalculated = true
    }
}

open class ParentDependentWrapContent(view: View) : WrapContent(view) {
    override val dependency = Dependency.parent
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
            null -> throw DimensionNotCalculatedException("${view.id}.type")
        }
    }
}


class CalculatedDimensionTypeNotDefinedError : Exception()

val View.wrapContent: WrapContent
    get() {
        return WrapContent(this)
    }

val TextView.wrapContent: ParentDependentWrapContent
    get() {
        return ParentDependentWrapContent(this)
    }
