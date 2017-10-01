package net.avatarapps.modernweb.core.components

import net.avatarapps.modernweb.core.components.layout.Layout
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

fun Layout.linearLayout(orientation: LinearLayout.Orientation, block: Layout.() -> Unit): Layout = LinearLayout(this, orientation).addToParent().visit(block)

fun Layout.view( block: View.() -> Unit): View = View(this).addToParent().visit(block)

fun <V : View> V.visit(block: V.() -> Unit): V {
    this.block()
    return this
}

fun <V : View> V.addToParent(): V {
    parent?.add(this)
    return this
}