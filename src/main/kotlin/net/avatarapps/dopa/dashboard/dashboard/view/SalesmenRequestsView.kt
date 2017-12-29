package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.independent.px


class SalesmenRequestsView : DashboardPlainViewContent("Salesmen requests") {
    override val plainPresenter = object : DashboardPlainPresenter() {
        override fun onViewCreated(view: View) {

        }
    }

    override var pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            textView {
                text = "No requests yet."
                textColor = DopaColors.separatorLight
                textSize = 24.px
                width = matchParent
                textAlign = TextView.TextAlign.Center
                marginTop = 16.px
            }
        }
    }
}
