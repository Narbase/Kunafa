@file:Suppress("unused")

package com.narbase.kunafa.core.css

import com.narbase.kunafa.core.components.Page


fun Page.classRuleSet(classNamePrefix: String? = null, rules: RuleSet.() -> Unit) =
        classRuleSet(this, classNamePrefix, rules)

fun Page.classRuleSet(classNamePrefix: String? = null, ruleSet: RuleSet) = classRuleSet(this, classNamePrefix, ruleSet)

fun Page.stringRuleSet(selector: String, rules: RuleSet.() -> Unit) = stringRuleSet(this, selector, rules)

fun Page.keyframes(userIndent: String, keyframesRules: Keyframes.() -> Unit) = keyframes(this, userIndent, keyframesRules)