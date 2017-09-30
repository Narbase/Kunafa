package net.avatarapps.modernweb.core.components

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
object Page : View() {
    var root: View? = null


    override fun render(){
        document.body?.style?.margin = "0"
        document.body?.style?.padding = "0"
        document.body?.style?.overflowY = "hidden"
        document.body?.style?.overflowX = "hidden"
        document.body?.clear()
        document.body?.append(root?.render())
    }
}