package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.ViewGroup
import kotlin.reflect.KProperty

expect class Reference<V : View> {

    operator fun getValue(parent: Any, property: KProperty<*>): V?

    operator fun setValue(parent: Any, property: KProperty<*>, value: V?)
}

expect class GroupReference<G : ViewGroup> {

    operator fun getValue(parent: Any, property: KProperty<*>): G

    operator fun setValue(any: Any, property: KProperty<*>, value: G)
}

expect inline fun <reified V : View> reference(): Reference<V>
expect inline fun <reified G : ViewGroup> reference(viewGroup: G): GroupReference<G>


