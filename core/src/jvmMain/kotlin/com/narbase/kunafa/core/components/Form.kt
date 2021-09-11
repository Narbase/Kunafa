@file:Suppress("unused")

package com.narbase.kunafa.core.components


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

actual class Form(parent: View? = null) : View(parent) {
    override val element = "form"
}


actual class FieldSet(parent: View? = null) : View(parent) {
    override val element = "fieldset"
}

actual class Legend(parent: View? = null) : View(parent) {

    override val element = "legend"
}
