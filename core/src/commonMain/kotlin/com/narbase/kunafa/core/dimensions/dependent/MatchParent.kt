@file:Suppress("unused")

package com.narbase.kunafa.core.dimensions.dependent

import com.narbase.kunafa.core.components.PageInterface
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.DynamicDimension

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

class MatchParent internal constructor(private val page: PageInterface) : DynamicDimension() {
    /*
    matchParent most of the time is dimension : 100%. However, when the element is inside a vertical layout (flex
    with column direction) or horizontal layout (flex with row dimension), and matchParent is on the perpendicular
    dimension to the flex, the 100% does not work in WebKit.
    To solve this, alignSelf : flex-stretch is used instead.
    In these cases, dimension should not be specified as 100%
    https://stackoverflow.com/questions/33636796/chrome-safari-not-filling-100-height-of-flex-parent
     */
    override fun configureHeight(ruleSet: RuleSet) {
        ruleSet.setProperty("height", "100%")
        if (ruleSet.selector is EmptySelector) return
        stringRuleSet(page, "${page.horizontalLayoutClass.selector} > ${ruleSet.selector}") {
            alignSelf = Alignment.Stretch
            height = wrapContent

        }
    }

    override fun configureWidth(ruleSet: RuleSet) {
        ruleSet.setProperty("width", "100%")
        if (ruleSet.selector is EmptySelector) return
        stringRuleSet(page, "${page.verticalLayoutClass.selector} > ${ruleSet.selector}") {
            alignSelf = Alignment.Stretch
            width = wrapContent
        }
    }
}

val RuleSet.matchParent: MatchParent
    get() {
        return MatchParent(page)
    }


