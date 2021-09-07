package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.View
import kotlin.reflect.KProperty

class Reference<V : View> {
    var view: V? = null

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(any: Any, property: KProperty<*>): V? {
        return this.view
    }

    operator fun setValue(any: Any, property: KProperty<*>, value: V?) {
        value?.attributes?.put("data-kssr-ref", "${any::class.simpleName}-${property.name}")
        view = value

    }

}

fun <V : View> reference() = Reference<V>()

