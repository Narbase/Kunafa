@file:Suppress("unused")

package com.narbase.kunafa.core.dimensions.dependent

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.dimensions.DynamicDimension

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/19/17.
 */
class Weight internal constructor(private val value: Int) : DynamicDimension() {

    override fun configureHeight(ruleSet: RuleSet) {

        ruleSet.setProperty("height", "auto")
        ruleSet.setProperty("flex", "$value 0 0px")
    }


    override fun configureWidth(ruleSet: RuleSet) {

        ruleSet.setProperty("width", "auto")
        ruleSet.setProperty("flex", "$value 0 0px")
    }
}

infix fun View.weightOf(value: Int): Weight {
    return Weight(value)
}
