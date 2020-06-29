@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.ClassSelector
import com.narbase.kunafa.core.css.RuleSet
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.clear

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
object Page : View(null) {

    override var isViewMounted: Boolean = true

    override val element: HTMLElement
        get() = document.body ?: document.createElement("body") as HTMLBodyElement

    var title: String
        get() = document.title
        set(value) {
            document.title = value
        }

    fun prepare() {
        id = "page"

        document.body?.style?.margin = "0"
        document.body?.style?.padding = "0"

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

    var useRtl: Boolean = false

}
