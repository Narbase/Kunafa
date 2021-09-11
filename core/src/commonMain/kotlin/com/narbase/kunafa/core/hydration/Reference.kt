package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.View
import kotlin.reflect.KProperty

expect class Reference<V : View> {

    operator fun getValue(any: Any, property: KProperty<*>): V?

    operator fun setValue(any: Any, property: KProperty<*>, value: V?)
}

//expect fun <V : View> reference(): Reference<V>
expect inline fun <reified V : View> reference(): Reference<V>


