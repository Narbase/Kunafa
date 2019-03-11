@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.lifecycle.LifecycleObserver

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/03/07.
 */
abstract class Component : LifecycleObserver {

    private var view: View? = null
    private val initializedView: View
        get() {
            val notNullView = view ?: detached.getView().apply { bind(this@Component) }
            if (view == null) {
                view = notNullView
            }
            return notNullView
        }

    protected abstract fun View?.getView(): View

    fun addToParent(parent: View?) {
        parent?.addChild(initializedView)
    }

    fun addToParentAfter(parent: View?, referenceView: View) {
        parent?.addChildAfter(initializedView, referenceView)
    }

    fun removeFromParent(parent: View?) {
        val validView = view ?: return
        parent?.removeChild(validView)
    }

}