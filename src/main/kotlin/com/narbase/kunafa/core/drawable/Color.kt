@file:Suppress("unused")

package com.narbase.kunafa.core.drawable

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
class Color() {

    private var red: Int = 255
    private var green: Int = 255
    private var blue: Int = 255
    private var alpha: Double = 1.0

    private var colorString: String? = null

    constructor(color: String) : this() {
        colorString = color
    }

    constructor(r: Int, g: Int, b: Int, a: Double = 1.0) : this() {
        red = r
        green = g
        blue = b
        alpha = a
    }

    companion object {

        val red = Color(255, 0, 0)
        val blue = Color(0, 0, 255)
        val white = Color(255, 255, 255)
        val black = Color(0, 0, 0)
        val transparent = Color(0, 0, 0, 0.0)
    }

    fun toCss(): String {
        val localColorString = colorString
        return if (localColorString == null) {
            "rgba($red,$green,$blue,$alpha)"
        } else {
            if (localColorString.startsWith("#")) localColorString else "#$localColorString"
        }
    }
}
