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

    override var width: Dimension = Point()
        set(value) {
            field = value
            if (value is DependentDimension) {
                value.type = DependentDimension.Type.width
            }

        }

    override var height: Dimension = Point()
        set(value) {
            field = value
            (value as? DependentDimension)?.type = DependentDimension.Type.height
        }


    open fun add(child: View) {
        addToElement(child)
        child.parent = this
        children.add(child)
    }

    fun addToElement(child: View) {
        element.append(child.element)
    }

    override fun render() {
        super.render()
        children.forEach { it.render() }

    }

    fun updateChildrenWidths() {
        if (!width.isCalculated)
            throw DimensionNotCalculatedException("$id.width")

        children
//                .filter { !it.width.isCalculated }
                .forEach { it.onParentWidthUpdated() }
    }

    fun updateChildrenHeights() {
        if (!height.isCalculated)
            throw DimensionNotCalculatedException("$id.heights")

        children
//                .filter { !it.height.isCalculated }
                .forEach { it.onParentHeightUpdated() }

    }

    open fun onContentWidthUpdated(){
        calculateWidthWithChildrenDependency()
    }

    open fun onContentHeightUpdated(){
        calculateHeightWithChildrenDependency()

    }

    override fun onParentWidthUpdated() {
        super.onParentWidthUpdated()
        updateChildrenWidths()
    }

    override fun onParentHeightUpdated() {
        super.onParentHeightUpdated()
        updateChildrenHeights()
    }

}