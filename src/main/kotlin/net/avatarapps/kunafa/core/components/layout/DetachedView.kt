package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.components.View

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/26/17.
 */
class DetachedView : Container(null) {

    override fun addChild(child: View) {
        if (children.size > 1)
            throw MoreThanOneChildInViewContainerException()
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