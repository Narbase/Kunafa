@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.lifecycle.LifecycleObserver
import com.narbase.kunafa.core.lifecycle.LifecycleOwner

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/03/07.
 */
abstract class Component : LifecycleObserver {

    private val view by lazy {
        detached.getView().apply { bind(this@Component) }
    }

    protected abstract fun View?.getView(): View

    override fun onViewMounted(lifecycleOwner: LifecycleOwner) {
    }

    override fun onViewRemoved(lifecycleOwner: LifecycleOwner) {
    }

    override fun viewWillBeRemoved(lifecycleOwner: LifecycleOwner) {
    }

    override fun viewWillMount(lifecycleOwner: LifecycleOwner) {
    }

    fun addToParent(parent: View?) {
        parent?.addChild(view)
    }

    fun addToParentAfter(parent: View?, referenceView: View) {
        parent?.addChildAfter(view, referenceView)
    }

    fun removeFromParent(parent: View?) {
        parent?.removeChild(view)
    }

}