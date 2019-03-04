package com.narbase.kunafa.core.viewcontroller

interface LifecycleOwner {
    fun bind(lifecycleObserver: LifecycleObserver)
    var lastLifecycleEvent: LifecycleEvent?
}