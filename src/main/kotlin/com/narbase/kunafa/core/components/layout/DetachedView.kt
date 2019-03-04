package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.View

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/26/17.
 */
class DetachedView : View(null) {

    override fun addChild(child: View) {
        if (children.size > 0)
            throw MoreThanOneChildInViewViewException()
        child.parent = this
        children.add(child)
    }

    override fun addToParent() {
    }

    val content: View?
        get() {
            return if (children.size > 0) children[0]
            else null
        }
}