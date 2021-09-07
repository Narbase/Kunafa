package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.Button
import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.button
import com.narbase.kunafa.core.components.view
import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class Reference<V : View>(private val viewClass: KClass<V>) {
    var view: V? = null

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(any: Any, property: KProperty<*>): V? {
        if (view != null) {
            return view
        }
        val node = document.querySelector("*[data-kssr-ref^='${any::class.simpleName}-${property.name}']") as? HTMLElement
                ?: return null

        val newView =
                when (viewClass) {
                    View::class -> UnknownMountedView.view(element = node as? HTMLElement ?: return null) { }
                    Button::class -> UnknownMountedView.button(element = node as? HTMLButtonElement ?: return null) { }
                    else -> null
                }
        newView?.removeRuleSet(View.baseClass)
        view = newView as? V
        return view
    }

    operator fun setValue(any: Any, property: KProperty<*>, value: V?) {
        throw RuntimeException("Cannot set value in front ent")
    }

}

inline fun <reified V : View> reference() = Reference(V::class)

