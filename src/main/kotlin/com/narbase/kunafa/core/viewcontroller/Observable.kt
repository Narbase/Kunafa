@file:Suppress("unused")

package com.narbase.kunafa.core.viewcontroller

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2018] Narbase Technologies
 * All Rights Reserved.
 * Created by ${user}
 * On: ${date}.
 */

class Observable<T>(val name: String) : LifecycleObserver {

    var value: T? = null
        set(value) {
            field = value
            observers.filter {
                it.key.lastLifecycleEvent == LifecycleEvent.ViewMounted
            }.forEach {
                it.value.forEach { it(value) }
            }
        }

    private var observers: MutableMap<LifecycleOwner, MutableList<(T?) -> Unit>> = mutableMapOf()

    fun observe(lifecycleOwner: LifecycleOwner, observer: (T?) -> Unit) {
        val previousList = observers[lifecycleOwner]
        if (previousList == null) {
            val list = mutableListOf(observer)
            observers[lifecycleOwner] = list
        } else {
            if (previousList.contains(observer).not()) {
                previousList.add(observer)
            }
        }
        lifecycleOwner.bind(this)
        if (lifecycleOwner.lastLifecycleEvent == LifecycleEvent.ViewMounted) {
            observers[lifecycleOwner]?.forEach { it(value) }
        }
    }

    override fun viewWillMount(lifecycleOwner: LifecycleOwner) {
    }

    override fun onViewMounted(lifecycleOwner: LifecycleOwner) {
        observers[lifecycleOwner]?.forEach {
            it(value)
        }
    }

    override fun viewWillBeRemoved(lifecycleOwner: LifecycleOwner) {
    }

    override fun onViewRemoved(lifecycleOwner: LifecycleOwner) {
//        observers.remove(lifecycleOwner)
    }

}