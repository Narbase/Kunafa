package net.avatarapps.booking

import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.weightOf
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color

fun main(args: Array<String>) {
    val lightGrey = Color.rgb(240, 240, 240)
    page {
        horizontalLayout {
            id = "horizontalLayout"
            background = lightGrey
            margin = 0.px
            padding = 0.px
            width = matchParent
            height = matchParent

            addSideBar(lightGrey)

            verticalLayout {
                id = "mainView"
                background = Color.white
                padding = 20.px
                marginStart = 2.px
                width = weightOf(1)
                height = matchParent
                isScrollableVertically = true
                alignItems = Alignment.Center
                addMainContent()
            }
        }
    }
}

private fun LinearLayout.addMainContent(): LinearLayout {
    return verticalLayout {
        id = "Vertical layout"
        background = Color.white
        padding = 20.px
        marginStart = 2.px
        width = matchParent
        height = matchParent
        isScrollableVertically = true
        alignItems = Alignment.Center

        val title = textView {
            text = "First view"
            width = matchParent
            height = wrapContent
            paddingStart = 10.px
            paddingEnd = 10.px
            marginTop = 8.px
            marginBottom = 8.px
            textAlign = TextView.TextAlign.Center
            textSize = 24.px
            textColor = Color.rgb(120, 120, 120)
        }

        button {
            id = "Button"
            width = matchParent
            height = 44.px
            marginBottom = 16.px
            onClick = {
                title.text = "Button clicked"
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

private fun LinearLayout.addSideBar(lightGrey: Color) {
    verticalLayout {
        id = "Vertical Layout"
        background = Color.white
        padding = 20.px
        width = 280.px
        height = matchParent
        isScrollableVertically = true
        alignItems = Alignment.Center

        textView {
            id = "HelloWorld"
            text = "First view"
            width = matchParent
            height = wrapContent
            paddingStart = 10.px
            paddingEnd = 10.px
            marginTop = 8.px
            marginBottom = 8.px
            textAlign = TextView.TextAlign.Center
        }

        addSeparator(lightGrey)

        textView {
            id = "HelloWorld"
            text = "Second view"
            width = matchParent
            height = wrapContent
            paddingStart = 10.px
            paddingEnd = 10.px
            marginTop = 8.px
            marginBottom = 8.px
            textAlign = TextView.TextAlign.Center
        }

        addSeparator(lightGrey)

        val thirdText = textView {
            id = "third_view"
            text = "Third view"
            width = matchParent
            height = wrapContent
            paddingStart = 10.px
            paddingEnd = 10.px
            marginTop = 8.px
            marginBottom = 8.px
            textAlign = TextView.TextAlign.Center
        }

        addSeparator(lightGrey)


//
//        val redView = view {
//            id = "redView"
//            width = matchParent
//            height = 40.px
//            marginBottom = 8.px
//            background = Color.red
//            onClick = {
//                println("Red view clicked")
//            }
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

