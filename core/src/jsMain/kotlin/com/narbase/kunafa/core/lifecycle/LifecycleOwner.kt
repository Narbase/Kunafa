package com.narbase.kunafa.core.lifecycle

interface LifecycleOwner {
    val isViewMounted: Boolean
    fun bind(lifecycleObserver: LifecycleObserver)
    fun unbind(lifecycleObserver: LifecycleObserver)
}