@file:Suppress("unused")

package com.narbase.kunafa.core.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

actual open class UList(parent: View? = null) : View(parent) {
    override val element = "ul"
}

actual open class ListItem(parent: View? = null) : View(parent) {
    override val element = "li"
}
