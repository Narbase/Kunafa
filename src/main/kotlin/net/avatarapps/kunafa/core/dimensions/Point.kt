package net.avatarapps.kunafa.core.dimensions

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

class Point(var value: Int = 0) : ExplicitDimension() {
    override var pixels: Int
        get() = value
        set(value) {

        }
}

val Int.points: Dimension
    get() {
        return Point(this)
    }