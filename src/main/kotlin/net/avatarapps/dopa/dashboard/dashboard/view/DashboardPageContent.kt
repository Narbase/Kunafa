package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.drugs.DrugsView
import net.avatarapps.dopa.dashboard.dashboard.view.reports.ReportsView
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.SalesmenView
import net.avatarapps.dopa.dashboard.dashboard.view.salesmenrequests.SalesmenRequestsView
import net.avatarapps.dopa.dashboard.dashboard.view.smartoffers.SmartOffersView
import net.avatarapps.dopa.dashboard.dashboard.view.zones.ZonesView
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.components.layout.ViewContainer
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.weightOf
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color
import org.w3c.dom.events.Event

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 12/12/17.
 */
class DashboardPageContent(
        private val navigator: DashboardNavigator
) : ViewContent() {
    private var mainView: ViewContainer? = null
    private val drugsView = DrugsView()
    private val salesmenRequestsView = SalesmenRequestsView()
    private val zonesView = ZonesView()
    private val salesmenView = SalesmenView()
    private val reportsView = ReportsView()
    private val smartOfferView = SmartOffersView()

    override fun DetachedView.contentDefinition() {
        horizontalLayout {
            id = "horizontalLayout"
            background = Color.white
            margin = 0.px
            padding = 0.px
            width = matchParent
            height = matchParent

            addSideBar(navigator)

            mainView = viewContainer {
                id = "mainView"
                background = Color.rgb(245, 245, 245)
                width = weightOf(1)
                height = matchParent
                isScrollableVertically = true
                alignItems = Alignment.Center
                content = salesmenView
            }
        }
    }

    private fun LinearLayout.addSideBar(navigator: DashboardNavigator) {

        verticalLayout {
            id = "sidebar"
            background = DopaColors.mainDark
            width = 210.px
            height = matchParent
            isScrollableVertically = false
            alignItems = Alignment.Start

            verticalLayout {
                height = wrapContent
                padding = 16.px
                width = matchParent
                marginBottom = 16.px

                imageView {
                    width = matchParent
                    img.src = "/public/img/logo.png"
                }
            }

            addMenuItem("salesmenView", "Salesmen") {
                mainView?.content = salesmenView
            }

            addMenuItem("drugsView", "Drugs") {
                mainView?.content = drugsView
            }

            addMenuItem("zonesView", "Zones") {
                mainView?.content = zonesView
            }

            addMenuItem("salesmenRequestsView", "Salesmen requests") {
                mainView?.content = salesmenRequestsView
            }


            addMenuItem("smartOfferView", "Smart offers") {
                mainView?.content = smartOfferView
            }

            addMenuItem("reportsView", "Reports") {
                mainView?.content = reportsView
            }

            addSeparator(DopaColors.main)

            addMenuItem("logoutView", "Logout") {
                navigator.onLogoutSelected()
            }
        }
    }

    private fun LinearLayout.addSeparator(lightGrey: Color) {
        view {
            width = matchParent
            height = 1.px
            background = lightGrey
        }
    }

    private fun LinearLayout.addMenuItem(idString: String, name: String, onClickListener: (Event) -> Unit) {
        textView {
            id = idString
            text = name
            width = matchParent
            height = wrapContent
            paddingStart = 20.px
            paddingEnd = 10.px
            paddingTop = 10.px
            paddingBottom = 10.px
            textColor = Color.white
            textAlign = TextView.TextAlign.Left
            onClick = onClickListener
            element.onmouseover = {
                background = DopaColors.mainLight
                element.style.cursor = "pointer"
                asDynamic()
            }
            element.onmouseleave = {
                background = Color.transparent
                element.style.cursor = ""
                asDynamic()

            }
        }
    }
}

interface DashboardNavigator {
    fun onLogoutSelected()
}
