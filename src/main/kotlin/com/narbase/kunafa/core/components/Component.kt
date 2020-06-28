@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.lifecycle.LifecycleObserver

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
abstract class Component(vararg val lifecycleObservers: LifecycleObserver) : LifecycleObserver {

    var rootView: View? = null
    private val initializedView: View
        get() = rootView ?: createView { getView() }

    val isInitialized
        get() = rootView != null

    fun createView(setup: View?.() -> View): View {
        val view = detached.setup()
        view.bind(this)
        lifecycleObservers.forEach { view.bind(it) }
        rootView = view
        view.postOnViewCreated()
        return view
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

    fun invalidateView() {
        rootView?.parent?.unMount(this)
        rootView = null
    }
}