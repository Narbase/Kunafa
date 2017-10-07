package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.*
import net.avatarapps.kunafa.core.dimensions.DependentDimension.Dependency


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

    abstract val wrappedContentWidth: IndependentDimension

    abstract val wrappedContentHeight: IndependentDimension

    open fun add(child: View) {
        addToElement(child)
        child.parent = this
        children.add(child)
    }

    fun addToElement(child: View) {
        element.append(child.element)
    }

    fun <C : Container> C.visitWithChildren(setupAndAddChildren: C.() -> Unit): C {
        println("1. Adding children")
        this.setupAndAddChildren()
        println("2. Adding to parent")
        this.addToParent()
        println("3. Calculating dimensions")
        when {
            this.width is IndependentDimension -> this.width.isCalculated = true
            this.width is DependentDimension -> when {
                (this.width as DependentDimension).dependency == Dependency.children -> calculateCdWidth()
                (this.width as DependentDimension).dependency == Dependency.parent
                        && this.parent?.width?.isCalculated ?: false -> calculatePdWidth()
            }
        }
        if (width.isCalculated) updateChildrenWidths()
        if (height.isCalculated) updateChildrenHeights()
        return this
    }

    override fun render() {
        super.render()
        children.forEach { it.render() }
    }

    private fun updateChildrenWidths() {
        if (!width.isCalculated)
            throw DimensionNotCalculatedException("$id.width")

        children
                .filter { !it.width.isCalculated }
                .forEach { it.onParentWidthUpdated() }
    }

    private fun updateChildrenHeights() {
        if (!height.isCalculated)
            throw DimensionNotCalculatedException("$id.heights")

        children
                .filter { !it.height.isCalculated }
                .forEach { it.onParentHeightUpdated() }

    }

    override fun onParentWidthUpdated(){
        super.onParentWidthUpdated()
        updateChildrenWidths()
    }

    override fun onParentHeightUpdated(){
        super.onParentHeightUpdated()
        updateChildrenHeights()
    }


    private fun calculateCdWidth() {
        if ((width as? DependentDimension)?.dependency == Dependency.children){
            (width as? DependentDimension)?.calculate()
        }

        if ((height as? DependentDimension)?.dependency == Dependency.children){
            (height as? DependentDimension)?.calculate()
        }
    }


    private fun calculatePdWidth() {
        if ((width as? DependentDimension)?.dependency == Dependency.parent){
            (width as? DependentDimension)?.calculate()
        }

        if ((height as? DependentDimension)?.dependency == Dependency.parent){
            (height as? DependentDimension)?.calculate()
        }
    }


}