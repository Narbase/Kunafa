package net.avatarapps.kunafa.core.dimensions

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
class Pixel(var value: Int = 0) : ExplicitDimension() {
    override var pixels = value
}

val Int.px: Pixel
    get() {
        return Pixel(this)
    }