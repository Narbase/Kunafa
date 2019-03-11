@file:Suppress("unused")

package com.narbase.kunafa.core.dimensions.dependent

import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.dimensions.DynamicDimension

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/1/17.
 */

internal class WrapContent : DynamicDimension() {

    override fun configureHeight(ruleSet: RuleSet) {
        ruleSet.setProperty("height", "auto")
    }

    override fun configureWidth(ruleSet: RuleSet) {
        ruleSet.setProperty("width", "auto")
    }
}

val RuleSet.wrapContent: DynamicDimension
    get() {
        return WrapContent()
    }

