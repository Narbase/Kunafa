package com.narbase.kunafa.core.components

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
actual class ImageView(parent: View? = null) : View(parent) {
    override val element = "img"

    var alt by attributeDelegate<String>()

    var src by attributeDelegate<String>()
}