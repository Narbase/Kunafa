@file:Suppress("unused")

package com.narbase.kunafa.core.css

@Suppress("MemberVisibilityCanBePrivate")
class RuleSet(var selector: Selector, val atRule: String? = null) {

    val rules: MutableSet<Rule<*>> = mutableSetOf()

    var subRuleSets: MutableSet<RuleSet>? = null

    var atRuleSets: MutableSet<RuleSet>? = null

    override fun hashCode(): Int {
        return rules.sumBy { it.hashCode() } +
                ((subRuleSets?.sumBy { it.hashCode() * it.selector.hashCode() }) ?: 0) +
                (atRuleSets?.sumBy { it.hashCode() * it.selector.hashCode() } ?: 0)
    }


    override fun equals(other: Any?): Boolean {
        return other is RuleSet &&
                selector == other.selector &&
                rules == other.rules &&
                subRuleSets == other.subRuleSets &&
                atRuleSets == other.atRuleSets &&
                atRule == other.atRule
    }

    fun <T> getProperty(name: String): T? {
        val list = rules.filterIsInstance<Rule<T>>()
        return list.find { it.name == name }?.value
    }

    operator fun set(key: String, value: String) {
        rules.add(Rule(key, value))
    }

    fun <T> setProperty(name: String, value: T) {
        rules.add(Rule(name, value))
    }

    fun toRulesList(): List<RuleSet> {
        val list = mutableListOf(this)
        if (atRule != null) return list
        subRuleSets?.let {
            it.forEach { set ->
                list.addAll(set.toRulesList())
            }
        }
        atRuleSets?.let {
            it.forEach { set ->
                list.addAll(set.toRulesList())
            }
        }
        return list
    }

    override fun toString(): String {
        return buildString {

            atRule?.let {
                append(atRule)
                append('{')
            }
            append(selector.toString())
            append("{")
            rules.forEach { rule ->
                append(rule.toString())
            }
            append('}')

            atRule?.let {
                subRuleSets?.forEach { subRuleSet ->
                    append(' ')
                    append(subRuleSet)
                }
                append('}')
            }
        }
    }

    fun addPseudo(name: String, rules: RuleSet.() -> Unit): RuleSet {
        val set = RuleSet(PseudoSelector(selector, name)).apply(rules)
        if (subRuleSets == null) subRuleSets = mutableSetOf()
        subRuleSets?.add(set)
        return set
    }

    fun addAtRule(name: String, rules: RuleSet.() -> Unit): RuleSet {
        if (atRuleSets == null) atRuleSets = mutableSetOf()
        val set = RuleSet(selector, atRule = "@$name").apply(rules)
        atRuleSets?.add(set)
        return set
    }

    fun addCompoundRuleSet(parentSelector: Selector, rules: RuleSet.() -> Unit): RuleSet {
        val set = RuleSet(CompoundSelector(listOf(parentSelector, selector))).apply(rules)
        if (subRuleSets == null) subRuleSets = mutableSetOf()
        subRuleSets?.add(set)
        return set
    }


    fun addCompoundClassRule(parentRuleSet: RuleSet, rules: RuleSet.() -> Unit): RuleSet {
        val set = RuleSet(CompoundSelector(listOf(parentRuleSet.selector, selector))).apply(rules)
        if (subRuleSets == null) subRuleSets = mutableSetOf()
        subRuleSets?.add(set)
        return set
    }
}