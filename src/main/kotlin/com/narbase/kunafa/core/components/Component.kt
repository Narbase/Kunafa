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

    protected var rootView: BaseElement? = null
    private val initializedView: BaseElement
        get() {
            val notNullView = rootView ?: createBaseElement { getView() }
            rootView = notNullView
            return notNullView
        }

    protected abstract fun BaseElement?.getView(): BaseElement

    fun addToParent(parent: BaseElement?) {
        parent?.mount(initializedView)
    }

    fun addToParentAfter(parent: BaseElement?, referenceView: BaseElement) {
        parent?.mountAfter(initializedView, referenceView)
    }

    fun removeFromParent(parent: BaseElement?) {
        val validView = rootView ?: return
        parent?.removeChild(validView)
    }

}