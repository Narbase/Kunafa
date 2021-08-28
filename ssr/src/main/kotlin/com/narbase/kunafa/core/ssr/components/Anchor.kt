@file:Suppress("unused")

package com.narbase.kunafa.core.ssr.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class Anchor(parent: View? = null) : View(parent) {
    override val element = "a"


    var text: String = ""
    var textBuilder: TextBuilder? = null
    var href
        get() = attributes.getOrDefault("href", "")
        set(value) {
            attributes["href"] = value
        }

    fun text(block: TextBuilder.() -> Unit) {
        if (textBuilder == null) textBuilder = TextBuilder()
        textBuilder?.block()
    }

    override fun configBegin(builder: StringBuilder) {
        builder.append(text)
        textBuilder?.let { builder.append(it.build()) }
    }


}
