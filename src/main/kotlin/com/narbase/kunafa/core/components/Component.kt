@file:Suppress("unused", "MemberVisibilityCanBePrivate")

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

    var rootView: View? = null
    private val initializedView: View
        get() {
            val notNullView = rootView ?: createView { getView() }
            rootView = notNullView
            return notNullView
        }

    protected abstract fun View?.getView(): View

    fun addToParent(parent: View?) {
        parent?.mount(initializedView)
    }

    fun addToParentAfter(parent: View?, referenceView: View) {
        parent?.mountAfter(initializedView, referenceView)
    }

    fun removeFromParent(parent: View?) {
        val validView = rootView ?: return
        parent?.removeChild(validView)
    }

}