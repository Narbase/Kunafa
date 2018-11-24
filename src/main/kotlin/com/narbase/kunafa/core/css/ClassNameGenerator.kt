package com.narbase.kunafa.core.css

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2018] Narbase Technologies
 * All Rights Reserved.
 * Created by ${user}
 * On: ${date}.
 */
object ClassNameGenerator {
    private var counter = 0

    fun getClassName(name: String? = null): String {
        return if (name == null) "c-${counter++}" else "$name-${counter++}"
    }

}