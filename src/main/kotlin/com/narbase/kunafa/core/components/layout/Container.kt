package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.View


/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
open class Container(parent: Container?) : View(parent) {
    val children: ArrayList<View> = arrayListOf()

    override fun configureElement() {
        super.configureElement()
        isScrollableVertically = false
        isScrollableHorizontally = false
    }

    open fun addChild(child: View) {
        addToElement(child)
        child.parent = this
        children.add(child)
    }

    private fun addToElement(child: View) {
        element.append(child.element)
    }

    open fun removeChild(child: View) {
        children.remove(child)
        element.removeChild(child.element)
        child.parent = null
    }

    open fun clearAllChildren(){
        while (element.firstChild != null) {
            element.firstChild?.let {
                element.removeChild(it)
            }
        }
    }

}