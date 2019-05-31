@file:Suppress("unused")

package com.narbase.kunafa.core.dimensions.dependent

import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.DynamicDimension

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/6/17.
 */

class MatchParent internal constructor() : DynamicDimension() {
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
        stringRuleSet("${LinearLayout.horizontalLayoutClass.selector} > ${ruleSet.selector}") {
            alignSelf = Alignment.Stretch
            height = wrapContent

        }
    }

    override fun configureWidth(ruleSet: RuleSet) {
        ruleSet.setProperty("width", "100%")
        stringRuleSet("${LinearLayout.verticalLayoutClass.selector} > ${ruleSet.selector}") {
            alignSelf = Alignment.Stretch
            width = wrapContent
        }
    }
}

val RuleSet.matchParent: MatchParent
    get() {
        return MatchParent()
    }


