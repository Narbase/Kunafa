package com.narbase.kunafa.core.ssr.components

import com.narbase.kunafa.core.components.ViewInterface

open class View(var parent: View? = null) : ViewInterface {
    override var id: String? = null
    open val element: String = "div"

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
            append(">")

            children.forEach { append(it.build()) }

            append("</$element>")
        }
        return builder.toString()
    }
}