@file:Suppress("unused")

package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.components.View

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
class AnchorLayout(
        parent: Container
) : Container(parent) {

    override fun configureElement() {
        super.configureElement()
        element.style.display = "inline-block"
        element.style.position = "relative"
    }

    override fun addChild(child: View) {
        child.element.style.position = "absolute"
        super.addChild(child)
    }


}

