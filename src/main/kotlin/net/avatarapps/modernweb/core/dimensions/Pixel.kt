package net.avatarapps.modernweb.core.dimensions

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
class Pixel(override var value: Int = 0) : Dimension() {
    override var type: Type = Type.PIXELS
    override var pixels = value
}

val Int.px: Dimension
    get() {
        return Pixel(this)
    }