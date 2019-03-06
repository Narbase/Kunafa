@file:Suppress("unused")

package com.narbase.kunafa.core.components.layout

import com.narbase.kunafa.core.components.View

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
class AnchorLayout(
        parent: View?
) : View(parent) {

    override fun configureElement() {
        super.configureElement()
        element.style.display = "inline-block"
        element.style.position = "relative"
    }

    override fun mountChild(child: View) {
        child.element.style.position = "absolute"
        super.mountChild(child)
    }


}

