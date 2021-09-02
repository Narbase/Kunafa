package com.narbase.kunafa.core.css

import kotlin.reflect.KProperty


@Suppress("MemberVisibilityCanBePrivate")
class RuleDelegate<T>(
        val name: String
) {

    operator fun getValue(ruleSet: RuleSet, property: KProperty<*>) = ruleSet.getProperty<T>(name)

    operator fun setValue(ruleSet: RuleSet, property: KProperty<*>, value: T) =
            ruleSet.setProperty(name, value)

}
