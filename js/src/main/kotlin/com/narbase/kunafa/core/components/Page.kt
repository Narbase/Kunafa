@file:Suppress("unused")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.*
import kotlinx.browser.document
import kotlinx.dom.clear
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLElement

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
object Page : PageInterface, View(null) {

    override var isViewMounted: Boolean = true
    val namedStyles = mutableMapOf<String, RuleSet>()

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

    override fun addToParent() {
        /*
        Should be empty. Page cannot be added to parent
         */
    }

    override val path: String?
        get() = "/"

    override var useRtl: Boolean = false
        set(value) {
            field = value
            if (value) {
                document.body?.style?.direction = "rtl"
            } else {
                document.body?.style?.direction = "ltr"
            }
        }
    override val styleSheetBuilder = SinglePageCssStyleSheetBuilder
    override val classNameGenerator = ClassNameGenerator()

    override val linearLayoutClass = classRuleSet {
        alignItems = Alignment.Start
        display = "inline-flex"
    }
    override val verticalLayoutClass = classRuleSet {
        flexDirection = "column"
    }
    override val horizontalLayoutClass = classRuleSet {
        flexDirection = "row"
    }
}
