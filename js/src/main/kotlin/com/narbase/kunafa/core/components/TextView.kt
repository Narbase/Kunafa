@file:Suppress("unused")

package com.narbase.kunafa.core.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
open class TextView(parent: View? = null) : View(parent) {

    var text
        get() = element.innerText
        set(value) {
            element.innerText = value
        }
}