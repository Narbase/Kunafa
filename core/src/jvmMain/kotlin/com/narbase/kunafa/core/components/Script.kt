package com.narbase.kunafa.core.components

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

open class Script(parent: View? = null) : View(parent) {
    override val element = "script"

    var async by attributeDelegate<Boolean>()
    var crossorigin by attributeDelegate<String>()
    var defer by attributeDelegate<Boolean>()
    var integrity by attributeDelegate<String>()
    var nomodule by attributeDelegate<Boolean>()
    var nonce by attributeDelegate<String>()
    var referrerpolicy by attributeDelegate<String>()
    var src by attributeDelegate<String>()
    var type by attributeDelegate<String>()
}