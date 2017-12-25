package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color


class SalesmenRequestsView : DashboardPlainViewContent("Salesmen requests") {
    override val pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            textView {
                text = "Requests"
            }
        }
    }
}
