package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.dimensions.IndependentDimension

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */
class ScrollView(parent: Container?) : Container(parent) {

    override val wrappedContentWidth: IndependentDimension
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val wrappedContentHeight: IndependentDimension
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    init {
        element.style.overflowX =  "scroll"
        element.style.overflowY =  "scroll"
    }
}