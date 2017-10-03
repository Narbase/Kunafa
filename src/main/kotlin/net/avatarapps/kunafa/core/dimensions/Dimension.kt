package net.avatarapps.kunafa.core.dimensions

import net.avatarapps.kunafa.core.components.layout.Container

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

abstract class Dimension {
    abstract var pixels: Int
}

abstract class CalculatedDimension(private var container: Container): Dimension() {
    var type: Type? = null
    abstract val dependsOnParent: Boolean

    enum class Type {
        width,
        height,
    }
}

abstract class ExplicitDimension : Dimension()
