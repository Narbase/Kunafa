package net.avatarapps.modernweb.core.components.layout

import net.avatarapps.modernweb.core.components.View
import net.avatarapps.modernweb.core.dimensions.CalculatedDimension
import net.avatarapps.modernweb.core.dimensions.Dimension
import net.avatarapps.modernweb.core.dimensions.ExplicitDimension
import net.avatarapps.modernweb.core.dimensions.Point


/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
abstract class Container(parent: Container?) : View(parent) {
    val children: ArrayList<View> = arrayListOf()

    override var width: Dimension = Point()
        set(value) {
            field = value
            (value as? CalculatedDimension)?.type = CalculatedDimension.Type.width
            updateElementWidth()
        }

    override var height: Dimension = Point()
        set(value) {
            field = value
            (value as? CalculatedDimension)?.type = CalculatedDimension.Type.height
            updateElementHeight()
        }

    abstract val wrappedContentWidth: ExplicitDimension

    abstract val wrappedContentHeight: ExplicitDimension

    open fun add(child: View){
        addToElement(child)
        child.parent = this
        children.add(child)
        updateElementDimensions()
    }

    fun addToElement(child: View){
        element.append(child.element)
    }
}