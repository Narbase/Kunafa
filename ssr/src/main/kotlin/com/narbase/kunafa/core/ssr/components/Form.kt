@file:Suppress("unused")

package com.narbase.kunafa.core.ssr.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

class Form(parent: View? = null) : View(parent) {
    override val element = "form"
}


class FieldSet(parent: View? = null) : View(parent) {
    override val element = "fieldset"
}

class Legend(parent: View? = null) : View(parent) {

    override val element = "legend"
}
