package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.View
import kotlin.reflect.KProperty

actual class Reference<V : View> {
    var view: V? = null

    @Suppress("UNCHECKED_CAST")
    actual operator fun getValue(any: Any, property: KProperty<*>): V? {
        return this.view
    }

    actual operator fun setValue(any: Any, property: KProperty<*>, value: V?) {
        value?.attributes?.put("data-kssr-ref", "${any::class.simpleName}-${property.name}")
        view = value

    }

}

actual inline fun <reified V : View> reference() = Reference<V>()

