package com.narbase.kunafa.core.css

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class ClassNameGenerator {
    private var counter = 0

    fun getClassName(name: String? = null): String {
        return if (name == null) "c-${counter++}" else "$name-${counter++}"
    }

}