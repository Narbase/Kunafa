package net.avatarapps.modernweb.core.dimensions

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

abstract class Dimension {

    abstract var value: Int
    abstract var type: Type
    abstract var pixels: Int

    enum class Type {
        PIXELS,
        POINTS
    }
}
