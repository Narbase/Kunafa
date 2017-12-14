package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.padding
import net.avatarapps.kunafa.core.components.textView
import net.avatarapps.kunafa.core.components.verticalLayout
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color

class ZonesView : ViewContent() {
    override fun DetachedView.contentDefinition() {
        verticalLayout {
            width = matchParent
            height = matchParent
            background = Color.white
            padding = 20.px
            textView {
                width = matchParent
                height = matchParent
                text = "Zones management"
                textAlign = TextView.TextAlign.Center
                textSize = 24.px
                textColor = Color.rgb(100, 100, 100)
            }
        }
    }
}