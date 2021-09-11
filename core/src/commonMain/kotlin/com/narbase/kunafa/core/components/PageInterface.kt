package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.ClassNameGenerator
import com.narbase.kunafa.core.css.CssStyleSheetBuilder
import com.narbase.kunafa.core.css.RuleSet

interface PageInterface : ViewInterface {

    var useRtl: Boolean

    val styleSheetBuilder: CssStyleSheetBuilder
    val classNameGenerator: ClassNameGenerator

    /* Global styles */
    val linearLayoutClass: RuleSet
    val verticalLayoutClass: RuleSet
    val horizontalLayoutClass: RuleSet
    val gridLayoutClass: RuleSet


}