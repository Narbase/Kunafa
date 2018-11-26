package com.narbase.kunafa.core.css

import kotlin.reflect.KProperty


@Suppress("MemberVisibilityCanBePrivate")
class ScrollableRuleDelegate(
        val isVertically: Boolean
) {

    operator fun getValue(ruleSet: RuleSet, property: KProperty<*>): Boolean {
        return if (isVertically) {
            ruleSet.getProperty<Any?>("overflow-y") == "auto" && ruleSet.getProperty<Any?>("min-height") != null
        } else {
            ruleSet.getProperty<Any?>("overflow-x") == "auto" && ruleSet.getProperty<Any?>("min-width") != null
        }
    }

    operator fun setValue(ruleSet: RuleSet, property: KProperty<*>, value: Boolean) {
        if (isVertically) {
            ruleSet.setProperty("overflow-y", if (value) "auto" else "visible")
            if (value && ruleSet.getProperty<Any?>("min-height") == null) {
                ruleSet.setProperty("min-height", "0")
            }
        } else {
            ruleSet.setProperty("overflow-x", if (value) "auto" else "visible")
            if (value && ruleSet.getProperty<Any?>("min-width") == null) {
                ruleSet.setProperty("min-width", "0")
            }
        }
    }

}
