package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.lifecycle.LifecycleObserver
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun <V : View> V.visit(lifecycleObserver: LifecycleObserver?, setup: V.() -> Unit): V {
    contract {
        callsInPlace(setup, InvocationKind.EXACTLY_ONCE)
    }

    lifecycleObserver?.let { bind(it) }
    this.addToParent()
    configureElement()
    this.setup()
    postOnViewCreated()
    return this
}
