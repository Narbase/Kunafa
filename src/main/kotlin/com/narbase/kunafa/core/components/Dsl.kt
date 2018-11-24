@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.Page.visit
import com.narbase.kunafa.core.components.layout.*
import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.css.Stylesheet
import com.narbase.kunafa.core.dimensions.IndependentDimension

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

fun detachedView(block: DetachedView.() -> Unit): DetachedView = DetachedView().visit(null, block)
fun Container.verticalLayout(block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Vertical).visit(null, block)
fun Container.horizontalLayout(block: LinearLayout.() -> Unit): LinearLayout = LinearLayout(this, LinearLayout.Orientation.Horizontal).visit(null, block)
fun Container.anchorLayout(block: AnchorLayout.() -> Unit): AnchorLayout = AnchorLayout(this).visit(null, block)
fun Container.viewContainer(block: ViewContainer.() -> Unit): ViewContainer = ViewContainer(this).visit(null, block)

fun Container.view(rules: (RuleSet.() -> Unit)? = null, block: View.() -> Unit): View = View(this).visit(rules, block)
fun Container.textView(block: TextView.() -> Unit): TextView = TextView(this).visit(null, block)
fun Container.textInput(block: TextInput.() -> Unit): TextInput = TextInput(this).visit(null, block)
fun Container.button(block: ButtonView.() -> Unit): ButtonView = ButtonView(this).visit(null, block)
fun Container.imageView(block: ImageView.() -> Unit): ImageView = ImageView(this).visit(null, block)
fun Container.checkbox(block: Checkbox.() -> Unit): Checkbox = Checkbox(this).visit(null, block)


// PROPERTIES:
var View.margin: IndependentDimension
    get() = throw Exception("Use View.margin properites")
    set(value) = this.setMargin(value)

var View.padding: IndependentDimension
    get() = throw Exception("Use View.padding properites")
    set(value) = this.setPadding(value)


// CSS

fun stylesheet(): Stylesheet = Stylesheet()
fun RuleSet.hover(rules: RuleSet.() -> Unit) = this.addPseudo(":hover", rules)