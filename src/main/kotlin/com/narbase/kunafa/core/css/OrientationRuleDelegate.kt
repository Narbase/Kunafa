package com.narbase.kunafa.core.css

import com.narbase.kunafa.core.components.layout.LinearLayout
import kotlin.reflect.KProperty


@Suppress("MemberVisibilityCanBePrivate")
class OrientationRuleDelegate {

    operator fun getValue(ruleSet: RuleSet, property: KProperty<*>): LinearLayout.Orientation? {
        val flexDirection = ruleSet.getProperty<Any?>("flex-direction")
        return when (flexDirection) {
            "row" -> LinearLayout.Orientation.Horizontal
            "column" -> LinearLayout.Orientation.Horizontal
            else -> null
        }
    }

    operator fun setValue(ruleSet: RuleSet, property: KProperty<*>, value: LinearLayout.Orientation?) {
        when (value) {
            LinearLayout.Orientation.Horizontal -> ruleSet.setProperty("flex-direction", "row")
            LinearLayout.Orientation.Vertical -> ruleSet.setProperty("flex-direction", "column")
            null -> ruleSet.setProperty("flex-direction", "initial")
        }
    }
}
