package com.narbase.kunafa.core.ViewContent

import com.narbase.kunafa.core.components.detachedView
import com.narbase.kunafa.core.components.layout.DetachedView
import com.narbase.kunafa.core.viewcontroller.LifecycleEvent
import com.narbase.kunafa.core.viewcontroller.LifecycleObserver
import com.narbase.kunafa.core.viewcontroller.LifecycleOwner

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 12/12/17.
 */
abstract class ViewContent : LifecycleOwner, LifecycleObserver {

    abstract fun DetachedView.contentDefinition()

    val detachedView by lazy {
        this@ViewContent.bind(this@ViewContent)
        detachedView {
            contentDefinition()
        }
    }

    private val lifecycleObserversList = mutableListOf<LifecycleObserver>()
    override var lastLifecycleEvent: LifecycleEvent? = null

    fun postViewWillBeCreated() {
        lastLifecycleEvent = LifecycleEvent.ViewWillBeCreated
        lifecycleObserversList.forEach { it.viewWillBeCreated(this) }
    }

    fun postOnViewCreated() {
        lastLifecycleEvent = LifecycleEvent.ViewCreated
        lifecycleObserversList.forEach { it.onViewCreated(this) }
    }

    fun postViewWillBeRemoved() {
        lastLifecycleEvent = LifecycleEvent.ViewWillBeRemoved
        lifecycleObserversList.forEach { it.viewWillBeRemoved(this) }
    }

    fun postOnViewRemoved() {
        lastLifecycleEvent = LifecycleEvent.ViewRemoved
        lifecycleObserversList.forEach { it.onViewRemoved(this) }
    }

    override fun bind(lifecycleObserver: LifecycleObserver) {
        lifecycleObserversList.add(lifecycleObserver)
    }

    override fun viewWillBeCreated(lifecycleOwner: LifecycleOwner) {

    }

    override fun onViewCreated(lifecycleOwner: LifecycleOwner) {

    }

    override fun viewWillBeRemoved(lifecycleOwner: LifecycleOwner) {

    }

    override fun onViewRemoved(lifecycleOwner: LifecycleOwner) {

    }

}