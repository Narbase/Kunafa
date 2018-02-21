package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Container
import com.narbase.kunafa.core.dimensions.IndependentDimension
import com.narbase.kunafa.core.dimensions.independent.px
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.clear

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
object Page : Container(null) {

    override fun updateElementWidth() {
        document.body?.style?.width = "${window.innerWidth}px"
    }

    override fun updateElementHeight() {
        document.body?.style?.height = "${window.innerHeight}px"
    }

    override fun addChild(child: View) {
        document.body?.append(child.element)
        children.add(child)
    }

    override fun removeChild(child: View) {
        children.remove(child)
        document.body?.removeChild(child.element)
        child.parent = null
    }

    var title: String
        get() = document.title
        set(value) {
            document.title = value
        }

    fun prepare() {
        id = "page"
        document.body?.style?.margin = "0"
        document.body?.style?.padding = "0"
        document.body?.style?.overflowY = "hidden"
        document.body?.style?.overflowX = "hidden"
        document.body?.clear()

        width = window.innerWidth.px
        height = window.innerHeight.px

        addOnResizedListener(this) {
            width = window.innerWidth.px
            height = window.innerHeight.px
        }

        window.onresize = {
            onResizedListeners.forEach { it.second() }
        }


    }

    override fun addToParent() {
        /*
        Should be empty. Page cannot be added to parent
         */
    }

}
