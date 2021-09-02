@file:Suppress("unused")

package com.narbase.kunafa.core.ssr.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class Radio(parent: View? = null) : View(parent) {

    override val element = "input"

    override fun configureElement(page: Page) {
        super.configureElement(page)
        attributes["type"] = "radio"
    }

    var isChecked by attributeDelegate<Boolean>("checked")
    var name by attributeDelegate<String>()

//    var onChange: ((Event) -> Unit)?
//        get() = element.onchange
//        set(value) {
//            element.onchange = value
//        }

}

