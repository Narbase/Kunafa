package net.avatarapps.dopa.dashboard.dashboard.view

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
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 12/12/17.
 */
class DashboardPageContent(
        private val navigator: DashboardNavigator
) : ViewContent() {
    private val lightPurple = Color.rgb(92, 65, 81)
    private var mainView: ViewContainer? = null
    private val drugsView = DrugsView()
    private val salesmenRequestsView = SalesmenRequestsView()
    private val zonesView = ZonesView()
    private val salesmenView = SalesmenView()
    private val reportsView = ReportsView()

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
                paddingStart = 2.px
                marginStart = 2.px
                width = weightOf(1)
                height = matchParent
                isScrollableVertically = true
                alignItems = Alignment.Center
                content = drugsView
            }
        }
    }

    private fun LinearLayout.addSideBar(navigator: DashboardNavigator) {

        verticalLayout {
            id = "sidebar"
            background = Color.rgb(65, 38, 58)
            width = 280.px
            paddingTop = 40.px
            height = matchParent
            isScrollableVertically = true
            alignItems = Alignment.Start

            addMenuItem("drugsView", "Drugs") {
                mainView?.content = drugsView
            }

            addSeparator(lightPurple)

            addMenuItem("salesmenView", "Salesmen") {
                mainView?.content = salesmenView
            }

            addSeparator(lightPurple)

            addMenuItem("salesmenRequestsView", "Salesmen requests") {
                mainView?.content = salesmenRequestsView
            }

            addSeparator(lightPurple)

            addMenuItem("zonesView", "Zones") {
                mainView?.content = zonesView
            }

            addSeparator(lightPurple)

            addMenuItem("reportsView", "Reports") {
                mainView?.content = reportsView
            }

            addSeparator(lightPurple)

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
            marginTop = 8.px
            marginBottom = 8.px
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
            marginTop = 8.px
            marginBottom = 8.px
            textColor = Color.white
            textAlign = TextView.TextAlign.Left
            onClick = onClickListener
        }
    }
}

interface DashboardNavigator {
    fun onLogoutSelected()
}
