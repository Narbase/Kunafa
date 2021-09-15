package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.ClassNameGenerator
import com.narbase.kunafa.core.css.Keyframes
import com.narbase.kunafa.core.css.RuleSet

interface PageInterface : ViewInterface {

    var useRtl: Boolean

    val classNameGenerator: ClassNameGenerator

    fun addRuleSetToDocument(ruleSet: RuleSet)

    fun addKeyframesToDocument(keyframes: Keyframes)

    /* Global styles */
    val linearLayoutClass: RuleSet
    val verticalLayoutClass: RuleSet
    val horizontalLayoutClass: RuleSet
    val gridLayoutClass: RuleSet


}