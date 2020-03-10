@file:Suppress("unused")

package com.narbase.kunafa.core.dimensions.dependent

import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.css.minHeight
import com.narbase.kunafa.core.css.minWidth
import com.narbase.kunafa.core.dimensions.DynamicDimension
import com.narbase.kunafa.core.dimensions.LinearDimension
import com.narbase.kunafa.core.dimensions.px

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class Weight internal constructor(private val value: Int, val basis: LinearDimension?) : DynamicDimension() {

    override fun configureHeight(ruleSet: RuleSet) {

        ruleSet.setProperty("height", "auto")
        ruleSet.setProperty("flex-grow", "$value")
        if (basis == null) {
            ruleSet.setProperty("flex-basis", "${value}px")
        } else {
            ruleSet.setProperty("flex-basis", basis.toString())
        }

        ruleSet.minHeight = 0.px
    }


    override fun configureWidth(ruleSet: RuleSet) {

        ruleSet.setProperty("width", "auto")
        ruleSet.setProperty("flex-grow", "$value")
        if (basis == null) {
            ruleSet.setProperty("flex-basis", "${value}px")
        } else {
            ruleSet.setProperty("flex-basis", basis.toString())
        }
        ruleSet.minWidth = 0.px
    }
}

fun RuleSet.weightOf(value: Int, basis: LinearDimension? = null): Weight {
    return Weight(value, basis)
}
