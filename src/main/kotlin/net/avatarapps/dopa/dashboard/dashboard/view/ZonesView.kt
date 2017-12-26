package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.DashboardPlainPresenter
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color
import net.avatarapps.kunafa.core.presenter.Presenter


class ZonesView : DashboardPlainViewContent("Zones management") {
    override val plainPresenter = object : DashboardPlainPresenter() {
        override fun onViewCreated(view: View) {

        }
    }
    override var pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            textView {
                text = "Zones management"
            }
        }
    }
}