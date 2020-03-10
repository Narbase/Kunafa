@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.ClassSelector
import com.narbase.kunafa.core.css.RuleSet
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.clear

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
object Page : View(null) {

    override var isViewMounted: Boolean = true

    override fun mount(child: View) {
        child.postViewWillMount()
        child.parent = this
        document.body?.append(child.element)
        children.add(child)
        child.postOnViewMounted()
    }

    override fun mountAfter(child: View, referenceNode: View) {
        child.postViewWillMount()
        document.body?.insertBefore(child.element, referenceNode.element.nextSibling)
        child.parent = this
        children.add(child)
        child.postOnViewMounted()
    }

    override fun removeChild(child: View) {
        children.remove(child)
        document.body?.removeChild(child.element)
        child.parent = null
    }

    var title: String
        get() = document.title
        set(value) {
            document.title = value
        }

    fun prepare() {
        id = "page"

        document.body?.style?.margin = "0"
        document.body?.style?.padding = "0"
        document.body?.style?.overflowY = "hidden"
        document.body?.style?.overflowX = "hidden"
        document.body?.style?.width = "100vw"
        document.body?.style?.height = "100vh"

        document.body?.clear()
    }

    override fun addRuleSet(ruleSet: RuleSet) {
        val selector = ruleSet.selector
        if (selector is ClassSelector) {
            val className = selector.name
            document.body?.addClass(className)
        }
    }

    override fun addToParent() {
        /*
        Should be empty. Page cannot be added to parent
         */
    }

    override val path: String?
        get() = "/"
}
