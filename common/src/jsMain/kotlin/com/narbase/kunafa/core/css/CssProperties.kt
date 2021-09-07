@file:Suppress("unused")

package com.narbase.kunafa.core.css


import com.narbase.kunafa.core.components.Page
import com.narbase.kunafa.core.components.Page.useRtl
import org.w3c.dom.css.CSSStyleDeclaration

var CSSStyleDeclaration.end: String
    get() = if (useRtl) left else right
    set(value) {
        if (useRtl) left = value else right = value
    }

var CSSStyleDeclaration.start: String
    get() = if (useRtl) right else left
    set(value) {
        if (useRtl) right = value else left = value
    }


fun classRuleSet(classNamePrefix: String? = null, rules: RuleSet.() -> Unit) =
        classRuleSet(Page, classNamePrefix, rules)

fun classRuleSet(classNamePrefix: String? = null, ruleSet: RuleSet) = classRuleSet(Page, classNamePrefix, ruleSet)

fun stringRuleSet(selector: String, rules: RuleSet.() -> Unit) = stringRuleSet(Page, selector, rules)

fun keyframes(userIndent: String, keyframesRules: Keyframes.() -> Unit) = keyframes(Page, userIndent, keyframesRules)