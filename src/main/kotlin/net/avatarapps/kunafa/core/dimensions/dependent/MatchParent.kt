package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.dimensions.DynamicDimension
import org.w3c.dom.HTMLElement

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/6/17.
 */

class MatchParent internal constructor(val view: View) : DynamicDimension() {
    override fun configure(element: HTMLElement, type: Type) {
        val parent = view.parent
        when (type) {
        /*
        matchParent most of the time is dimension : 100%. However, when the element is inside a vertical layout (flex
        with column direction) or horizontal layout (flex with row dimension), and matchParent is on the perpendicular
        dimension to the flex, the 100% does not work in WebKit.
        To solve this, alignSelf : flex-stretch is used instead.
        This function however is not only used to configure the direct elements of views, but also the internal elements
        (img in ImageView, span in TextView..etc). In these cases, match parent should always be dimension : 100%.
        Therefore, we check if the element is direct element of the view or internal element.
         */
            Type.height -> {
                if (parent.isHorizontalLayout() && view.element == element) {
                    element.style.alignSelf = Alignment.Stretch.cssName
                } else {
                    element.style.height = "100%"
                }
            }
            Type.width -> {
                if (parent.isVerticalLayout() && view.element == element) {
                    element.style.alignSelf = Alignment.Stretch.cssName

                } else {
                    element.style.width = "100%"
                }
            }
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


