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

    override fun DetachedView.contentDefinition() {
        horizontalLayout {
            id = "horizontalLayout"
            background = Color.white
            margin = 0.px
            padding = 0.px
            width = matchParent
            height = matchParent
            addSideBar(navigator)

            mainView =  viewContainer {
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
            id = "Vertical Layout"
            background = Color.rgb(65, 38, 58)
            width = 280.px
            paddingTop = 40.px
            height = matchParent
            isScrollableVertically = true
            alignItems = Alignment.Start

            textView {
                id = "drugsView"
                text = "Drugs"
                width = matchParent
                height = wrapContent
                paddingStart = 20.px
                paddingEnd = 10.px
                marginTop = 8.px
                textColor = Color.white
                marginBottom = 8.px
                textAlign = TextView.TextAlign.Left
                onClick = {

                    mainView?.content = drugsView
                }
            }

            addSeparator(lightPurple)

            textView {
                id = "salesmenView"
                text = "Salesmen"
                width = matchParent
                height = wrapContent
                paddingStart = 20.px
                paddingEnd = 10.px
                marginTop = 8.px
                textColor = Color.white
                marginBottom = 8.px
                textAlign = TextView.TextAlign.Left
                onClick = {

                    mainView?.content = drugsView
                }
            }

            addSeparator(lightPurple)

            textView {
                id = "salesmenRequestsView"
                text = "Salesmen requests"
                width = matchParent
                height = wrapContent
                paddingStart = 20.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textColor = Color.white
                textAlign = TextView.TextAlign.Left
                onClick = {
                    mainView?.content = salesmenRequestsView
                }
            }

            addSeparator(lightPurple)

            textView {
                id = "zonesView"
                text = "Zones"
                width = matchParent
                height = wrapContent
                paddingStart = 20.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textColor = Color.white
                textAlign = TextView.TextAlign.Left
                onClick = {
                    mainView?.content = zonesView
                }
            }

            addSeparator(lightPurple)

            textView {
                id = "reportsView"
                text = "Reports"
                width = matchParent
                height = wrapContent
                paddingStart = 20.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textColor = Color.white
                textAlign = TextView.TextAlign.Left
                onClick = {
                    mainView?.content = zonesView
                }
            }

            addSeparator(lightPurple)

            textView {
                id = "logoutView"
                text = "Logout"
                width = matchParent
                height = wrapContent
                paddingStart = 20.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textColor = Color.white
                textAlign = TextView.TextAlign.Left
                onClick = {
                    navigator.onLogoutSelected()
                }
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

}

interface DashboardNavigator {
    fun onLogoutSelected()
}
