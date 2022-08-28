package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.ViewGroup
import kotlin.reflect.KProperty

actual class Reference<V : View> {
    var view: V? = null

    @Suppress("UNCHECKED_CAST")
    actual operator fun getValue(parent: Any, property: KProperty<*>): V? {
        return this.view
    }

    actual operator fun setValue(parent: Any, property: KProperty<*>, value: V?) {
        val parentRef = (parent as? ViewGroup)?.ref ?: parent::class.simpleName
        value?.attributes?.put("data-kssr-ref", "$parentRef-${property.name}")
        view = value

    }

}

actual class GroupReference<G : ViewGroup>(private val group: G) {

    actual operator fun getValue(parent: Any, property: KProperty<*>): G {
        val parentRef = (parent as? ViewGroup)?.ref ?: parent::class.simpleName
        group.ref = "$parentRef-${property.name}"
        return group
    }

    actual operator fun setValue(any: Any, property: KProperty<*>, value: G) {
    }
}

actual inline fun <reified V : View> reference() = Reference<V>()
actual inline fun <reified G : ViewGroup> reference(viewGroup: G) = GroupReference(viewGroup)

