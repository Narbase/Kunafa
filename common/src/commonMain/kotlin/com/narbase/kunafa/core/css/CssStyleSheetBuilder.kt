package com.narbase.kunafa.core.css

import com.narbase.kunafa.core.components.PageInterface

abstract class CssStyleSheetBuilder(open val page: PageInterface) {

    abstract fun addRuleSetToDocument(ruleSet: RuleSet)

    abstract fun addKeyframesToDocument(keyframes: Keyframes)

}