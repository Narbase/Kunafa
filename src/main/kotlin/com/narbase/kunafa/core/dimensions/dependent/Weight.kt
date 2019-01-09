@file:Suppress("unused")

package com.narbase.kunafa.core.dimensions.dependent

import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.css.minHeight
import com.narbase.kunafa.core.css.minWidth
import com.narbase.kunafa.core.dimensions.DynamicDimension
import com.narbase.kunafa.core.dimensions.px

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
        ruleSet.setProperty("flex-grow", "$value")
        ruleSet.setProperty("flex-basis", "${value}px")
        ruleSet.minHeight = 0.px
    }


    override fun configureWidth(ruleSet: RuleSet) {

        ruleSet.setProperty("width", "auto")
        ruleSet.setProperty("flex-grow", "$value")
        ruleSet.setProperty("flex-basis", "${value}px")
        ruleSet.minWidth = 0.px
    }
}

infix fun RuleSet.weightOf(value: Int): Weight {
    return Weight(value)
}
