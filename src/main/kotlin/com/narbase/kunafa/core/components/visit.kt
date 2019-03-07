package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.lifecycle.LifecycleObserver

fun <V : View> V.visit(lifecycleObserver: LifecycleObserver?, setup: V.() -> Unit): V {
    lifecycleObserver?.let { bind(it) }
    postViewWillMount()
    configureElement()
    this.setup()
    this.addToParent()
    postOnViewMounted()
    return this
}
