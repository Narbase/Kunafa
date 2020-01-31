@file:Suppress("unused")

package com.narbase.kunafa.core.lifecycle

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2018] Narbase Technologies
 * All Rights Reserved.
 * Created by ${user}
 * On: ${date}.
 */

open class Observable<T> {

    open var value: T? = null
        set(value) {
            field = value
            observers?.forEach { it(value) }
        }
    private var observers: MutableList<(T?) -> Unit>? = null

    open fun observe(observer: (T?) -> Unit) {
        if (observers?.contains(observer) == true) {
            return
        }
        if (observers == null) {
            observers = mutableListOf(observer)
        } else {
            observers?.add(observer) ?: throw ConcurrentModificationException()
        }
        observer(value)
    }

    open fun clearObservers() {
        observers?.clear()
    }
}