@file:Suppress("unused")

package com.narbase.kunafa.core.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
actual open class TextView(parent: View? = null) : View(parent) {

    //todo: move text and text builder to view
    var text
        get() = element.innerText
        set(value) {
            element.innerText = value
        }
}