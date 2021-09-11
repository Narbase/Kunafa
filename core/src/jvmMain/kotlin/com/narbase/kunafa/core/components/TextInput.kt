@file:Suppress("unused")

package com.narbase.kunafa.core.components

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class TextInput(parent: View? = null) : View(parent) {

    override val element = "input"


    var placeholder by attributeDelegate<String>()

    var type by attributeDelegate<String>()

    var alt by attributeDelegate<String>()

//    var onChange: ((Event) -> Unit)?
//        get() = element.onchange
//        set(value) {
//            element.onchange = value
//        }

}