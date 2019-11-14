package com.narbase.kunafa.core.lifecycle

interface LifecycleOwner {
    var isViewMounted: Boolean
    fun bind(lifecycleObserver: LifecycleObserver)
}