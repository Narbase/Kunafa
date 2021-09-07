package com.narbase.kunafa.core.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
@Suppress("unused")
class Checkbox(parent: View? = null) : View(parent) {

    override val element = "input"

    override fun configureElement(page: Page<*>) {
        super.configureElement(page)
        attributes["type"] = "checkbox"
    }

    var isChecked by attributeDelegate<Boolean>("checked")

//    var onChange: ((Event) -> Unit)?
//        get() = element.onchange
//        set(value) {
//            element.onchange = value
//        }

}