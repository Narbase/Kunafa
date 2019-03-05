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
                it.key.lastLifecycleEvent == LifecycleEvent.ViewCreated
            }.forEach {
                it.value.forEach { it(value) }
            }
        }

    var observers: MutableMap<LifecycleOwner, MutableList<(T?) -> Unit>> = mutableMapOf()

    fun observe(lifecycleOwner: LifecycleOwner, observer: (T?) -> Unit) {
        val previousList = observers[lifecycleOwner]
        if (previousList == null) {
            val list = mutableListOf(observer)
            observers[lifecycleOwner] = list
        } else {
            previousList.add(observer)
        }
        lifecycleOwner.bind(this)
        if (lifecycleOwner.lastLifecycleEvent == LifecycleEvent.ViewCreated) {
            observers[lifecycleOwner]?.forEach { it(value) }
        }
    }

    override fun viewWillBeCreated(lifecycleOwner: LifecycleOwner) {
        console.log("$name viewWillBeCreated")

    }

    override fun onViewCreated(lifecycleOwner: LifecycleOwner) {
        console.log("$name onViewCreated: has ${observers[lifecycleOwner]?.size} observers")
        observers[lifecycleOwner]?.forEach {
            it(value)
        }
    }

    override fun viewWillBeRemoved(lifecycleOwner: LifecycleOwner) {
    }

    override fun onViewRemoved(lifecycleOwner: LifecycleOwner) {
    }

}