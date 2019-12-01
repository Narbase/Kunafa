package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.lifecycle.LifecycleObserver

fun <V : BaseElement> V.visit(lifecycleObserver: LifecycleObserver?, setup: V.() -> Unit): V {
    lifecycleObserver?.let { bind(it) }
    this.addToParent()
    configureElement()
    this.setup()
    postOnViewCreated()
    return this
}

fun Component.createBaseElement(setup: BaseElement?.() -> BaseElement): BaseElement {
    val view = detached.setup()
    view.bind(this)
    view.postOnViewCreated()
    return view
}
