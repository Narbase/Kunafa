@file:Suppress("unused")

package com.narbase.kunafa.core.dimensions.dependent

import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.dimensions.DynamicDimension

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
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

