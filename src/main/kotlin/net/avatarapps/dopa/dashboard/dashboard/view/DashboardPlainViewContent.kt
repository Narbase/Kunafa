package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.DashboardPlainPresenter
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.presenter.Presenter

abstract class DashboardPlainViewContent(val title: String) : ViewContent() {
    abstract var pageViewContent: ViewContent
    abstract val plainPresenter: DashboardPlainPresenter

    override fun DetachedView.contentDefinition() {
        verticalLayout {
            padding = 16.px
            width = matchParent
            height = matchParent
            presenter = plainPresenter
            textView {
                text = title
                textColor = DopaColors.mainLight
                textSize = 24.px
            }

            view {
                width = matchParent
                height = 1.px
                background = DopaColors.separatorLight
                marginTop = 16.px
                marginBottom = 16.px
            }

            plainPresenter.mainViewContent = viewContainer {
                width = matchParent
                height = matchParent
                content = pageViewContent
            }
        }
    }

}