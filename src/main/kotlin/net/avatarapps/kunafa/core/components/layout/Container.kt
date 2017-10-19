package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.DependentDimension
import net.avatarapps.kunafa.core.dimensions.Dimension
import net.avatarapps.kunafa.core.dimensions.DimensionNotCalculatedException
import net.avatarapps.kunafa.core.dimensions.independent.Point


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

    open fun add(child: View) {
        addToElement(child)
        child.parent = this
        children.add(child)
    }

    fun addToElement(child: View) {
        element.append(child.element)
    }

}