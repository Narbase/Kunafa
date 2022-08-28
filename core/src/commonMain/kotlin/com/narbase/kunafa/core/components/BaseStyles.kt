package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.px

class BaseStyles(private val page: PageInterface) {

    private fun classRuleSet(rules: RuleSet.() -> Unit) =
        classRuleSet(page, null, rules)


    val linearLayoutClass = classRuleSet {
        alignItems = Alignment.Start
        display = "inline-flex"
    }

    val verticalLayoutClass = classRuleSet {
        flexDirection = "column"
    }

    val horizontalLayoutClass = classRuleSet {
        flexDirection = "row"
    }

    val gridLayoutClass = classRuleSet {
        display = "grid"
    }

    val baseClass = classRuleSet {
        boxSizing = "border-box"
        margin = 0.px
        padding = 0.px
        flexShrink = "0"
    }

    val invisibleClass = classRuleSet {
        display = "none !important"
    }

    val verticalScrollLayoutClass = classRuleSet {
        isScrollableVertically = true
        isScrollableHorizontally = false
    }
    val horizontalScrollLayoutClass = classRuleSet {
        isScrollableHorizontally = true
        isScrollableVertically = false
    }
}