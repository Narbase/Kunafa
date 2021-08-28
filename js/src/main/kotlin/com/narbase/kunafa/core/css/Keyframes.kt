package com.narbase.kunafa.core.css

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

@Suppress("MemberVisibilityCanBePrivate")
class Keyframes(val userIdent: String) {

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
        val set = RuleSet(StringSelector(name)).apply(rules)
        subRuleSets.add(set)
        return set
    }

}