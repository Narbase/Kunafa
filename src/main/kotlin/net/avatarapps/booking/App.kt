package net.avatarapps.booking

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

var mainView: ViewContainer? = null
fun main(args: Array<String>) {
    val lightGrey = Color.rgb(240, 240, 240)
    val firstView = getFirstView()
    val secondView = getSecondView()
    val thirdView = getThirdView()

    page {
        horizontalLayout {
            id = "horizontalLayout"
            background = lightGrey
            margin = 0.px
            padding = 0.px
            width = matchParent
            height = matchParent

            addSideBar(lightGrey, firstView, secondView, thirdView)

            mainView =  viewContainer {
                id = "mainView"
                background = Color.rgb(245, 245, 245)
                padding = 20.px
                marginStart = 2.px
                width = weightOf(1)
                height = matchParent
                isScrollableVertically = true
                alignItems = Alignment.Center
                content = firstView
            }
        }
    }
}

private fun getThirdView(): DetachedView {
    return detachedView {
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

private fun getSecondView(): DetachedView {
    return detachedView {
        verticalLayout {
            width = matchParent
            height = matchParent
            background = Color.white
            padding = 20.px
            textView {
                width = matchParent
                height = matchParent
                text = "Hi, I am second view"
                textAlign = TextView.TextAlign.Center
                textSize = 24.px
                textColor = Color.rgb(100, 100, 100)
            }
        }
    }
}

private fun getFirstView(): DetachedView {
    return detachedView {
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

private fun LinearLayout.addSideBar(
        lightGrey: Color,
        firstView: DetachedView,
        secondView: DetachedView,
        thirdView: DetachedView) {
    verticalLayout {
        id = "Vertical Layout"
        background = Color.white
        padding = 20.px
        width = 280.px
        height = matchParent
        isScrollableVertically = true
        alignItems = Alignment.Center

        val text1 = textView {
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
                console.log(mainView)
                mainView?.content = firstView
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
                mainView?.content = secondView
            }
        }

        addSeparator(lightGrey)

        val thirdText = textView {
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
                mainView?.content = thirdView
            }
        }

        addSeparator(lightGrey)

//
//        val redView = view {
//            id = "redView"
//            width = matchParent
//            height = 40.px
//            marginBottom = 8.px
//            background = Color.red
//        }
//
//        addSeparator(lightGrey)

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

