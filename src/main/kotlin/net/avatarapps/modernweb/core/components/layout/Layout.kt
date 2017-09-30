package net.avatarapps.modernweb.core.components.layout

import net.avatarapps.modernweb.core.components.View

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
open class Layout : View() {
    val children: ArrayList<View> = arrayListOf()

    fun add(child: View){
        children.add(child)
    }
}