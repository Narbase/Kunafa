package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.View

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/29/17.
 */
open class ViewContainer(
        parent: Container?
) : Container(parent) {

    override fun addChild(child: View) {
        if (children.size > 1)
            throw MoreThanOneChildInViewContainerException()
        super.addChild(child)
    }

    var content: ViewContent? = null
        set(value) {
            value?.content?.content?.let {
                field = value

                this.children.forEach {
                    this.removeChild(it)
                }
                this.addChild(it)
            }
        }

//    var content: DetachedView? = null
//        set(value) {
//            field = value
//            children.forEach {
//                removeChild(it)
//            }
//            value?.content?.let {
//                console.log(it)
//                addChild(it)
//            }
//        }
}

class MoreThanOneChildInViewContainerException : Exception() {

}
