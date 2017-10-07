package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.Page.visitWithChildren
import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.components.layout.LinearLayout

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
    Page.visitWithChildren(setupAndAddChildren)
    Page.render()
}

fun Container.verticalLayout(block: Container.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.vertical).visitWithChildren(block)
fun Container.horizontalLayout(block: Container.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.horizontal).visitWithChildren(block)

fun Container.view(block: View.() -> Unit): View = View(this).visit(block)



