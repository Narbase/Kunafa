package com.narbase.kunafa.core.components

import kotlin.reflect.KProperty


@Suppress("MemberVisibilityCanBePrivate")
class AttributeDelegate<T : Any>(private val attributeName: String? = null, private val defaultValue: T? = null) {

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(view: View, property: KProperty<*>): T? {

        return if (defaultValue != null)
            view.attributes.getOrDefault(attributeName ?: property.name, defaultValue) as? T
        else
            view.attributes[attributeName ?: property.name] as? T

    }

    operator fun setValue(view: View, property: KProperty<*>, value: T?) {
        if (value == null) {
            view.attributes.remove(attributeName ?: property.name)
            return
        }
        view.attributes[attributeName ?: property.name] = value
    }

}

fun <T : Any> attributeDelegate(attributeName: String? = null, defaultValue: T? = null) = AttributeDelegate(attributeName, defaultValue)
