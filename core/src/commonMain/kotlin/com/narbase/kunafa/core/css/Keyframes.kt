package com.narbase.kunafa.core.css

import com.narbase.kunafa.core.components.PageInterface

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

@Suppress("MemberVisibilityCanBePrivate")
class Keyframes(val page: PageInterface, val userIdent: String) {

    var subRuleSets: MutableSet<RuleSet> = mutableSetOf()

    override fun toString(): String {
        return buildString {

            append("@keyframes $userIdent")
            append('{')

            subRuleSets.forEach { subRuleSet ->
                append(' ')
                append(subRuleSet)
            }
            append('}')
        }
    }

    fun addKeyframeRule(name: String, rules: RuleSet.() -> Unit): RuleSet {
        val set = RuleSet(page, StringSelector(name)).apply(rules)
        subRuleSets.add(set)
        return set
    }

}