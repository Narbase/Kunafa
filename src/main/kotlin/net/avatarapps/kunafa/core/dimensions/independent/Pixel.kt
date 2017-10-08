package net.avatarapps.kunafa.core.dimensions.independent

import net.avatarapps.kunafa.core.dimensions.IndependentDimension

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
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