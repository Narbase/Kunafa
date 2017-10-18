package net.avatarapps.kunafa

import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color

fun main(args: Array<String>) {
    page {
        horizontalLayout {
            id = "horizontalLayout"
            background = Color.rgb(200, 200, 200)
            setMargin(10.px)
            setPadding(20.px)
            width = matchParent
            height = matchParent
            verticalLayout {
                id = "Vertical Layout"
                background = Color.rgb(220, 220, 220)
                setPadding(20.px)
                width = wrapContent
                height = matchParent
                isScrollableVertically = true
                textView {
                    id = "HelloWorld"
                    text = "Hello world Hello world Hello world\n\n\n Hello world Hello world Hello\n world Hello world Hello world "
                    width = 120.px
                    height = wrapContent
                    paddingStart = 10.px
                    paddingEnd = 10.px
                    marginBottom = 24.px
                }

//                button {
//                    id = "Button"
//                    width = 200.px
//                    height = 44.px
//                    marginBottom = 16.px
//                }

                val redView = view {
                    id = "redView"
                    width = 150.px
                    height = 500.px
                    background = Color.red
                }
                view {
                    id = "anotherView"
                    width = 300.px
                    height = 200.px
                    background = Color.blue
                }

                view {
                    id = "ThirdView"
                    width = 130.px
                    height = 400.px
                    background = Color.rgb(255, 0, 255)
                }
            }

//            verticalLayout {
//                id = "Vertical layout"
//                background = Color.rgb(220, 220, 220)
//                width = 800.px
//                height = matchParent
//                setPadding(20.px)
//                marginStart = 30.px
//                isScrollableVertically = true
//
//                view {
//                    id = "View1"
//                    width = matchParent
//                    height = 500.px
//                    background = Color.rgb(0, 255, 0)
//                }
//                view {
//                    id = "View2"
//                    width = 500.px
//                    height = 500.px
//                    background = Color.rgb(0, 0, 255)
//                }
//                view {
//                    id = "View3"
//                    width = 500.px
//                    height = 500.px
//                    background = Color.rgb(255, 0, 0)
//                }
//            }
        }
    }
}

