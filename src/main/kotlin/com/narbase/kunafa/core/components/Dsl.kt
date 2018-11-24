@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.Page.visit
import com.narbase.kunafa.core.components.layout.*
import com.narbase.kunafa.core.css.RuleSet

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

fun page(setupAndAddChildren: Container.() -> Unit = {}) {
    Page.prepare()
    Page.visit(null, setupAndAddChildren)
}

fun detachedView(rules: (RuleSet.() -> Unit)? = null, block: DetachedView.() -> Unit): DetachedView = DetachedView().visit(rules, block)
fun Container.verticalLayout(rules: (RuleSet.() -> Unit)? = null, block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Vertical).visit(rules, block)
fun Container.horizontalLayout(rules: (RuleSet.() -> Unit)? = null, block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Horizontal).visit(rules, block)
fun Container.anchorLayout(rules: (RuleSet.() -> Unit)? = null, block: AnchorLayout.() -> Unit): AnchorLayout = AnchorLayout(this).visit(rules, block)
fun Container.viewContainer(rules: (RuleSet.() -> Unit)? = null, block: ViewContainer.() -> Unit): ViewContainer = ViewContainer(this).visit(rules, block)

fun Container.view(rules: (RuleSet.() -> Unit)? = null, block: View.() -> Unit): View = View(this).visit(rules, block)
fun Container.textView(rules: (RuleSet.() -> Unit)? = null, block: TextView.() -> Unit): TextView = TextView(this).visit(rules, block)
fun Container.textInput(rules: (RuleSet.() -> Unit)? = null, block: TextInput.() -> Unit): TextInput = TextInput(this).visit(rules, block)
fun Container.button(rules: (RuleSet.() -> Unit)? = null, block: ButtonView.() -> Unit): ButtonView = ButtonView(this).visit(rules, block)
fun Container.imageView(rules: (RuleSet.() -> Unit)? = null, block: ImageView.() -> Unit): ImageView = ImageView(this).visit(rules, block)
fun Container.checkbox(rules: (RuleSet.() -> Unit)? = null, block: Checkbox.() -> Unit): Checkbox = Checkbox(this).visit(rules, block)


// PROPERTIES:
//var View.margin: LinearDimension
//    get() = throw Exception("Use View.margin properties")
//    set(value) = this.setMargin(value)
//
//var View.padding: LinearDimension
//    get() = throw Exception("Use View.padding properties")
//    set(value) = this.setPadding(value)
