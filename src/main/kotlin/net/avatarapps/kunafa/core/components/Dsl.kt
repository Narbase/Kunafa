package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.Page.visit
import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.dimensions.Dimension
import net.avatarapps.kunafa.core.dimensions.IndependentDimension

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

fun page(setupAndAddChildren: Container.() -> Unit = {}){
    Page.prepare()
    Page.visit(setupAndAddChildren)
}

fun Container.verticalLayout(block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Vertical).visit(block)
fun Container.horizontalLayout(block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Horizontal).visit(block)

fun Container.view(block: View.() -> Unit): View = View(this).visit(block)
fun Container.textView(block: TextView.() -> Unit): TextView = TextView(this).visit(block)
fun Container.button(block: ButtonView.() -> Unit): ButtonView = ButtonView(this).visit(block)



// PROPERTIES:
var View.margin: IndependentDimension
    get() = throw Exception("Use View.margin properites")
    set(value) = this.setMargin(value)

var View.padding: IndependentDimension
    get() = throw Exception("Use View.padding properites")
    set(value) = this.setPadding(value)



