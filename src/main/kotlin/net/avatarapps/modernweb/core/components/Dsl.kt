package net.avatarapps.modernweb.core.components

import net.avatarapps.modernweb.core.components.layout.Container
import net.avatarapps.modernweb.core.components.layout.LinearLayout

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

fun page(block: Page.() -> Unit = {}){
    Page.prepare()
    Page.block()
}

fun Container.linearLayout(orientation: LinearLayout.Orientation, block: Container.() -> Unit): Container = LinearLayout(this, orientation).visit(block).addToParent()

fun Container.view(block: View.() -> Unit): View = View(this).visit(block).addToParent()

fun <V : View> V.visit(block: V.() -> Unit): V {
    this.block()
    return this
}

fun <V : View> V.addToParent(): V {
    parent?.add(this)
    return this
}