@file:Suppress("unused")

package com.narbase.kunafa.core.lifecycle

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
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