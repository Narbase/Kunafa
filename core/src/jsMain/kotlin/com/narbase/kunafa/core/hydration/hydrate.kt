package com.narbase.kunafa.core.hydration

inline fun <reified R> hydrate(ref: R, block: R.() -> Unit) {
    val refName = R::class.simpleName
    if (refName == MetaData.getPageRef()) {
        ref.block()
    }
}