@file:Suppress("unused")

package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.css.classRuleSet
import com.narbase.kunafa.core.css.isScrollableHorizontally
import com.narbase.kunafa.core.css.isScrollableVertically


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
        this.addRuleSet(containerClass)
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
        child.viewController?.viewWillBeRemoved(child)
        children.remove(child)
        element.removeChild(child.element)
        child.parent = null
        child.viewController?.onViewRemoved(child)
    }

    open fun clearAllChildren() {
        children.forEach { child ->
            child.viewController?.viewWillBeRemoved(child)
            children.remove(child)
        }
        while (element.firstChild != null) {
            element.firstChild?.let {
                element.removeChild(it)
            }
        }
        children.forEach { child ->
            child.viewController?.onViewRemoved(child)
            children.remove(child)
        }
    }

    companion object {
        val containerClass = classRuleSet {
            isScrollableVertically = false
            isScrollableHorizontally = false
        }
    }
}