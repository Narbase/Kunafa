@file:Suppress("unused")

package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.ViewContent.ViewContent
import com.narbase.kunafa.core.components.View

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/29/17.
 */
open class ViewContainer(
        parent: View?
) : View(parent) {

//    override fun addChild(child: View) {
//        if (children.size > 0)
//            throw MoreThanOneChildInViewContainerException()
//        super.addChild(child)
//    }

    var viewContent: ViewContent? = null
        set(value) {
            viewContent?.postViewWillBeRemoved()
            clearAllChildren()
            viewContent?.postOnViewRemoved()

            field = value
            value?.postViewWillBeCreated()
            value?.detachedView?.children?.forEach {
                this.mountChild(it)
            }
            value?.postOnViewCreated()
        }


    /*
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

     */

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
