package net.avatarapps.modernweb.core.dimensions

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

class Point(override var value: Int = 0) : Dimension() {
    override var type: Type = Type.POINTS

    override var pixels: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {

        }
}

val Int.points: Dimension
    get() {
        return Point(this)
    }