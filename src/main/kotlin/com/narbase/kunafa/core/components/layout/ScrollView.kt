package com.narbase.kunafa.core.components.layout

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */
class ScrollView(parent: Container?) : Container(parent) {

    init {
        element.style.overflowX =  "scroll"
        element.style.overflowY =  "scroll"
    }
}