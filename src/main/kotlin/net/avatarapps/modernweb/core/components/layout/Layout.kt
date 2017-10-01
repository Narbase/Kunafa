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
open class Layout(parent: Layout?) : View(parent) {
    val children: ArrayList<View> = arrayListOf()

    open fun add(child: View){
        element.append(child.element)
        children.add(child)
    }
}