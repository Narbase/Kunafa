package net.avatarapps.dopa.dashboard.dashboard

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
        val navigator: DashboardNavigator
) : ViewContent() {
    val lightGrey = Color.rgb(240, 240, 240)

    private var mainView: ViewContainer? = null

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
                padding = 20.px
                marginStart = 2.px
                width = weightOf(1)
                height = matchParent
                isScrollableVertically = true
                alignItems = Alignment.Center
                content = FirstView()
            }
        }
    }

    private fun LinearLayout.addSideBar(navigator: DashboardNavigator) {

        verticalLayout {
            id = "Vertical Layout"
            background = Color.white
            padding = 20.px
            width = 280.px
            height = matchParent
            isScrollableVertically = true
            alignItems = Alignment.Center

            textView {
                id = "firstView"
                text = "First view"
                width = matchParent
                height = wrapContent
                paddingStart = 10.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textAlign = TextView.TextAlign.Center
                onClick = {

                    mainView?.content = FirstView()
                }
            }

            addSeparator(lightGrey)

            textView {
                id = "secondView"
                text = "Second view"
                width = matchParent
                height = wrapContent
                paddingStart = 10.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textAlign = TextView.TextAlign.Center
                onClick = {
                    mainView?.content = SecondView()
                }
            }

            addSeparator(lightGrey)

            textView {
                id = "thirdView"
                text = "Third view"
                width = matchParent
                height = wrapContent
                paddingStart = 10.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textAlign = TextView.TextAlign.Center
                onClick = {
                    mainView?.content = ThirdView()
                }
            }

            addSeparator(lightGrey)

            textView {
                id = "logoutView"
                text = "Logout"
                width = matchParent
                height = wrapContent
                paddingStart = 10.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textAlign = TextView.TextAlign.Center
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

class ThirdView : ViewContent() {
    override fun DetachedView.contentDefinition() {
        verticalLayout {
            width = matchParent
            height = matchParent
            background = Color.white
            padding = 20.px
            textView {
                width = matchParent
                height = matchParent
                text = "Welcome\nI am third view"
                textAlign = TextView.TextAlign.Center
                textSize = 24.px
                textColor = Color.rgb(100, 100, 100)
            }
        }
    }
}

class SecondView : ViewContent() {
    override fun DetachedView.contentDefinition() {
        verticalLayout {
            width = matchParent
            height = matchParent
            background = Color.white
            padding = 20.px

            textView {
                width = matchParent
                height = wrapContent
                text = "Hi, I am second view"
                textAlign = TextView.TextAlign.Center
                textSize = 24.px
                textColor = Color.rgb(100, 100, 100)
            }

            textInput {
                width = matchParent
                height = wrapContent
                placeholder = "Fill me in, baby"
                textAlign = TextView.TextAlign.Left
                textSize = 24.px
                textColor = Color.rgb(100, 100, 100)
                onChange = { event ->
                    println("Printed: ${this@textInput.text}")
                }
            }
        }
    }
}

class FirstView : ViewContent() {
    override fun DetachedView.contentDefinition() {
        verticalLayout {
            id = "Vertical layout"
            background = Color.white
            padding = 20.px
            marginStart = 2.px
            width = matchParent
            height = matchParent
            isScrollableVertically = true
            alignItems = Alignment.Center

            textView {
                text = "First view"
                width = matchParent
                height = wrapContent
                paddingStart = 10.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textAlign = TextView.TextAlign.Center
                textSize = 24.px
                textColor = Color.rgb(100, 100, 100)
            }
            val title = textView {
                text = "Button has never been clicked :("
                width = matchParent
                height = wrapContent
                paddingStart = 10.px
                paddingEnd = 10.px
                marginTop = 8.px
                marginBottom = 8.px
                textAlign = TextView.TextAlign.Center
                textSize = 18.px
                textColor = Color.rgb(120, 120, 120)
            }
            var i = 0

            button {
                id = "Button"
                width = matchParent
                height = 44.px
                marginBottom = 16.px
                onClick = {
                    i++
                    title.text = "Button's clicked $i time${"s".takeIf { i > 1 } ?: ""}"
                }
            }
            view {
                id = "View1"
                width = matchParent
                height = 500.px
                background = Color.rgb(200, 200, 220)
                marginBottom = 10.px
            }
            view {
                id = "View2"
                width = 500.px
                height = 500.px
                marginBottom = 10.px
                background = Color.rgb(140, 140, 180)
            }

            view {
                id = "View3"
                width = 500.px
                height = 500.px
                background = Color.rgb(180, 180, 200)
            }
        }
    }
}

interface DashboardNavigator {
    fun onLogoutSelected()
}
