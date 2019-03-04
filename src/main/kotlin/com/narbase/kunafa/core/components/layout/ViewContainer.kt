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
open class ViewView(
        parent: View?
) : View(parent) {

    override fun addChild(child: View) {
        if (children.size > 0)
            throw MoreThanOneChildInViewViewException()
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

class MoreThanOneChildInViewViewException : Exception() {

}
