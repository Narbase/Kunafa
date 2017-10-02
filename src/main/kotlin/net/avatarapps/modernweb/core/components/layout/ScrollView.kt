package net.avatarapps.modernweb.core.components.layout

import net.avatarapps.modernweb.core.components.View

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */
class ScrollView(parent: Container?) : View(parent) {
    init {
        element.style.overflowX =  "scroll"
        element.style.overflowY =  "scroll"
    }
}