package com.narbase.kunafa.core.ssr.components

import com.narbase.kunafa.core.components.ViewInterface

open class View(var parent: View? = null) : ViewInterface {
    override var id: String? = null
    open val element: String = "div"
    val attributes = mutableMapOf<String, String>()

    override val children: MutableSet<View> = mutableSetOf()

    internal open fun addToParent() {
        parent?.mount(this)
    }

    open fun mount(child: View) {
        child.parent = this
        children.add(child)
    }

    open fun build(): String {
        val builder = StringBuilder()
        builder.apply {
            append("<$element")
            id?.let { append(""" id="$it" """) }

            attributes.forEach { key, value ->
                append(""" $key="$value" """)
            }
            append(">")

            configBegin(builder)

            children.forEach { append(it.build()) }

            append("</$element>")
        }
        return builder.toString()
    }

    open fun configBegin(builder: StringBuilder) {

    }

    open fun removeChild(child: View) {
        if (children.contains(child).not()) {
            return
        }
        children.remove(child)
        child.parent = null
    }
}