@file:Suppress("unused")

package com.narbase.kunafa.core.css

import com.narbase.kunafa.core.dimensions.Dimension
import com.narbase.kunafa.core.dimensions.DynamicDimension
import kotlin.reflect.KProperty

@Suppress("MemberVisibilityCanBePrivate")
class DynamicDimensionRuleDelegate(
        val name: String
) {

    operator fun getValue(ruleSet: RuleSet, property: KProperty<*>): Dimension? {
        return ruleSet.getProperty<DynamicDimension>(name)
    }

    operator fun setValue(ruleSet: RuleSet, property: KProperty<*>, value: Dimension?) {
        if (value is DynamicDimension) {
            when (property.name) {
                RuleSet::width.name -> {
                    value.configureWidth(ruleSet)
                }
                RuleSet::height.name -> {
                    value.configureHeight(ruleSet)
                }
                else -> {
                    throw IllegalArgumentException("DynamicDimensionRuleDelegate can only set width, and height")
                }
            }
        } else {
            ruleSet.setProperty(name, value)
        }
    }

}
