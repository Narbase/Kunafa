package com.narbase.kunafa.core.css

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/09/21.
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