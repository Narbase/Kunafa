package net.avatarapps.kunafa.core.dimensions.independent

import net.avatarapps.kunafa.core.dimensions.IndependentDimension

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
class Pixel(override var pixels: Int = 0) : IndependentDimension() {

}

val Int.px: Pixel
    get() {
        return Pixel(this)
    }