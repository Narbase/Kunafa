package net.avatarapps.modernweb.core.components

import net.avatarapps.modernweb.core.components.layout.Layout
import kotlin.browser.document
import kotlin.dom.clear

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
object Page : Layout(null) {

    override fun add(child: View){
        document.body?.append(child.element)
        children.add(child)
    }

    fun prepare(){
        document.body?.style?.margin = "0"
        document.body?.style?.padding = "0"
        document.body?.style?.overflowY = "hidden"
        document.body?.style?.overflowX = "hidden"
        document.body?.clear()
    }
}