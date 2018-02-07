package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.dimensions.IndependentDimension

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
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