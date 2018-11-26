@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Container

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/9/17.
 */
open class TextView(parent: Container? = null) : View(parent) {

    var text = ""
        set(value) {
            field = value
            element.innerHTML = value
        }
}