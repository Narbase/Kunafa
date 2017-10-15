package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.dimensions.DependentDimension
import net.avatarapps.kunafa.core.dimensions.Dimension
import net.avatarapps.kunafa.core.dimensions.IndependentDimension
import net.avatarapps.kunafa.core.dimensions.independent.px
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.clear

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
object Page : Container(null) {

    override var width: Dimension = object :DependentDimension() {
        override val dependency = Dependency.parent
        override var isCalculated = true
        override fun calculate() {
            calculatedDimension = window.innerWidth.px
        }
    }

    override var height: Dimension = object :DependentDimension() {
        override val dependency = Dependency.parent
        override var isCalculated = true
        override fun calculate() {
            calculatedDimension = window.innerHeight.px
        }
    }


    override val wrappedContentWidth: IndependentDimension
        get() = throw DimensionNotAvailableOnViewException()
    override val wrappedContentHeight: IndependentDimension
        get() = throw DimensionNotAvailableOnViewException()

    override fun add(child: View) {
        document.body?.append(child.element)
        children.add(child)
    }

    fun prepare() {
        window.onresize = {
            element.onresize?.let { it1 -> it1(asDynamic()) }
            render()
        }
        id = "Page"
        element.id = id?:""
        document.body?.style?.margin = "0"
        document.body?.style?.padding = "0"
        document.body?.style?.overflowY = "hidden"
        document.body?.style?.overflowX = "hidden"
        document.body?.clear()
        (width as DependentDimension).calculate()
        (height as DependentDimension).calculate()
    }

    override fun updateElementWidth() {
        super.updateElementWidth()
        document.body?.style?.width = element.style.width

    }

    override fun updateElementHeight() {
        document.body?.style?.height = element.style.height
        super.updateElementHeight()

    }

}
