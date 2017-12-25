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


class ZonesView : DashboardPlainViewContent("Zones management") {
    override val pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            textView {
                text = "Zones management"
            }
        }
    }
}