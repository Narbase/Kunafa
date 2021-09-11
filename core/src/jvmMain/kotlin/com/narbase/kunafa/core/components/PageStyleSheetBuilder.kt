package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.CssStyleSheetBuilder
import com.narbase.kunafa.core.css.Keyframes
import com.narbase.kunafa.core.css.RuleSet

class PageStyleSheetBuilder(override val page: Page<*>) : CssStyleSheetBuilder(page) {

    override fun addRuleSetToDocument(ruleSet: RuleSet) {
        page.namedStyles[ruleSet.selector.toString()] = ruleSet
    }

    override fun addKeyframesToDocument(keyframes: Keyframes) {
        page.keyframes.add(keyframes)
    }

}