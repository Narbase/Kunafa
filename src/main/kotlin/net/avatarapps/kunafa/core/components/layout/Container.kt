package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.components.View


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

    open fun add(child: View) {
        addToElement(child)
        child.parent = this
        children.add(child)
    }

    private fun addToElement(child: View) {
        element.append(child.element)
    }

    fun removeChild(child: View) {
        children.remove(child)
        element.removeChild(child.element)
        child.parent = null

    }

}