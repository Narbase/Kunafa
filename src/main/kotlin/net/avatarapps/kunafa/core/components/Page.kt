package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
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

    override val wrappedContentWidth: IndependentDimension
        get() = throw DimensionNotAvailableOnViewException()
    override val wrappedContentHeight: IndependentDimension
        get() = throw DimensionNotAvailableOnViewException()

    override fun add(child: View) {
        document.body?.append(child.element)
        children.add(child)
    }

    fun prepare() {
        width = window.innerWidth.px
        height = window.innerHeight.px
        window.onresize = {
            element.onresize?.let { it1 -> it1(asDynamic()) }
        }
        id = "Page"
        element.id = id?:""
        document.body?.style?.margin = "0"
        document.body?.style?.padding = "0"
        document.body?.style?.overflowY = "hidden"
        document.body?.style?.overflowX = "hidden"
        document.body?.clear()

        addOnResizedListener(this){
            width = window.innerWidth.px
            height = window.innerHeight.px
        }
    }

    override fun addToParent() {
    }

    override fun updateElementWidth() {
        document.body?.style?.width = "${window.innerWidth}px"
    }

    override fun updateElementHeight() {
        document.body?.style?.height = "${window.innerHeight}px"
    }

}
