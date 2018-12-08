@file:Suppress("unused")

package com.narbase.kunafa.core.dimensions.dependent

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.layout.Container
import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.css.Alignment
import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.css.alignSelf
import com.narbase.kunafa.core.dimensions.DynamicDimension

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/6/17.
 */

class MatchParent internal constructor(val view: View) : DynamicDimension() {
    /*
    matchParent most of the time is dimension : 100%. However, when the element is inside a vertical layout (flex
    with column direction) or horizontal layout (flex with row dimension), and matchParent is on the perpendicular
    dimension to the flex, the 100% does not work in WebKit.
    To solve this, alignSelf : flex-stretch is used instead.
    This function however is not only used to configure the direct elements of views, but also the internal elements
    (img in ImageView, span in TextView..etc). In these cases, match parent should always be dimension : 100%.
    Therefore, we check if the element is direct element of the view or internal element.
     */
    override fun configureHeight(ruleSet: RuleSet) {
        val parent = view.parent
        ruleSet.setProperty("height", "100%")
        if (parent.isHorizontalLayout()/* && view.element == element*/) {
            ruleSet.apply {
                alignSelf = Alignment.Stretch
            }
        } else {
        }
    }

    override fun configureWidth(ruleSet: RuleSet) {
        val parent = view.parent
        ruleSet.setProperty("width", "100%")
        if (parent.isVerticalLayout() /*&& view.element == element*/) {
            ruleSet.apply {
                alignSelf = Alignment.Stretch
            }
        } else {
        }

    }

    private fun Container?.isVerticalLayout() =
            this is LinearLayout && orientation == LinearLayout.Orientation.Vertical

    private fun Container?.isHorizontalLayout() =
            this is LinearLayout && orientation == LinearLayout.Orientation.Horizontal

}

val View.matchParent: MatchParent
    get() {
        return MatchParent(this)
    }


