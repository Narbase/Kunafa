package com.narbase.kunafa.core.lifecycle

interface LifecycleOwner {
    fun bind(lifecycleObserver: LifecycleObserver)
    var lastLifecycleEvent: LifecycleEvent?
}