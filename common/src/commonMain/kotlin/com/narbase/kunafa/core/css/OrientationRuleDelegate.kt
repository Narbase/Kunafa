package com.narbase.kunafa.core.css

import com.narbase.kunafa.core.components.layout.LinearLayoutOrientation
import kotlin.reflect.KProperty


@Suppress("MemberVisibilityCanBePrivate")
class OrientationRuleDelegate {

    operator fun getValue(ruleSet: RuleSet, property: KProperty<*>): LinearLayoutOrientation? {
        val flexDirection = ruleSet.getProperty<Any?>("flex-direction")
        return when (flexDirection) {
            "row" -> LinearLayoutOrientation.Horizontal
            "column" -> LinearLayoutOrientation.Horizontal
            else -> null
        }
    }

    operator fun setValue(ruleSet: RuleSet, property: KProperty<*>, value: LinearLayoutOrientation?) {
        when (value) {
            LinearLayoutOrientation.Horizontal -> ruleSet.setProperty("flex-direction", "row")
            LinearLayoutOrientation.Vertical -> ruleSet.setProperty("flex-direction", "column")
            null -> ruleSet.setProperty("flex-direction", "initial")
        }
    }
}
