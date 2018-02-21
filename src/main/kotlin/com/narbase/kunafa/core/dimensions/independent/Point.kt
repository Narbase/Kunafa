package com.narbase.kunafa.core.dimensions.independent

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

class Point(var value: Int = 0) : com.narbase.kunafa.core.dimensions.IndependentDimension() {
    override var pixels: Int
        get() = value
        set(value) {

        }
}

val Int.points: com.narbase.kunafa.core.dimensions.CalculatedDimension
    get() {
        return com.narbase.kunafa.core.dimensions.independent.Point(this)
    }