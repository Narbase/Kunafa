package com.narbase.kunafa.core.ssr.components

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
open class TextView(parent: View? = null) : View(parent) {

    var text: String = ""
    var textBuilder: TextBuilder? = null

    fun text(block: TextBuilder.() -> Unit) {
        if (textBuilder == null) textBuilder = TextBuilder()
        textBuilder?.block()
    }

    override fun configBegin(builder: StringBuilder) {
        builder.append(text)
        textBuilder?.let { builder.append(it.build()) }
    }
}