package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
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

    override var width: Dimension = window.innerHeight.px
    override var height: Dimension = window.innerHeight.px

    override val wrappedContentWidth: IndependentDimension
        get() = TODO("not implemented")
    override val wrappedContentHeight: IndependentDimension
        get() = TODO("not implemented")

    override fun add(child: View){
        document.body?.append(child.element)
        children.add(child)
    }

    fun prepare(){
        id = "Page"
        document.body?.style?.margin = "0"
        document.body?.style?.padding = "0"
        document.body?.style?.overflowY = "hidden"
        document.body?.style?.overflowX = "hidden"
        document.body?.clear()
    }
}