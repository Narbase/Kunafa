package com.narbase.kunafa.core.css


class Stylesheet

@Suppress("MemberVisibilityCanBePrivate")
class RuleSet(val selector: Selector) {

    val rules: MutableSet<Rule<*>> = mutableSetOf()

    fun <T> getProperty(name: String): T? {
        val list = rules.filterIsInstance<Rule<T>>()
        return list.find { it.name == name }?.value
    }

    fun <T> setProperty(name: String, value: T) {
        rules.add(Rule(name, value))
    }

    override fun toString(): String {
        return buildString {
            append(selector.toString())
            append("{")
            rules.forEachIndexed { index, rule ->
                if (index != 0)
                    append(',')
                append(rule.toString())
            }
            append('}')
        }
    }
}