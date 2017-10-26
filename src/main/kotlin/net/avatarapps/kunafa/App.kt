package net.avatarapps.kunafa

import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
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
            setMargin(0.px)
            setPadding(0.px)
            width = matchParent
            height = matchParent
            verticalLayout {
                id = "Vertical Layout"
                background = Color.white
                setPadding(20.px)
                width = weightOf(1)
                height = matchParent
                isScrollableVertically = true
                alignItems = Alignment.Center
                textView {
                    id = "HelloWorld"
                    text = "First item in list and also a very long string that takes more than one line but it is wrap content"
                    width = wrapContent
                    height = wrapContent
                    paddingStart = 10.px
                    paddingEnd = 10.px
                    marginTop = 8.px
                    marginBottom = 8.px
                }
                view {
                    width = matchParent
                    height = 1.px
                    background = lightGrey
                    marginTop = 8.px
                    marginBottom = 8.px
                }

                textView {
                    id = "HelloWorld"
                    text = "Second item in list"
                    width = wrapContent
                    height = wrapContent
                    paddingStart = 10.px
                    paddingEnd = 10.px
                    marginTop = 8.px
                    marginBottom = 8.px
                }
                view {
                    width = matchParent
                    height = 1.px
                    background = lightGrey
                    marginTop = 8.px
                    marginBottom = 8.px
                }
                textView {
                    id = "HelloWorld"
                    text = "Third item in list"
                    width = matchParent
                    height = wrapContent
                    paddingStart = 10.px
                    paddingEnd = 10.px
                    marginTop = 8.px
                    marginBottom = 8.px
                }
                view {
                    width = matchParent
                    height = 1.px
                    background = lightGrey
                    marginTop = 8.px
                    marginBottom = 8.px
                }

                button {
                    id = "Button"
                    width = matchParent
                    height = 44.px
                    marginBottom = 16.px
                }

                val redView = view {
                    id = "redView"
                    width = matchParent
                    height = 40.px
                    marginBottom = 40.px
                    background = Color.red
                }
                view {
                    id = "anotherView"
                    width = matchParent
                    height = 40.px
                    marginBottom = 40.px
                    background = Color.blue
                }

                view {
                    id = "ThirdView"
                    width = 130.px
                    height = 40.px
                    marginBottom = 40.px
                    background = Color.rgb(255, 0, 255)
                }

                view {
                    id = "ThirdView"
                    width = 370.px
                    height = 40.px
                    marginBottom = 40.px
                    background = lightGrey
                }

                textView {
                    id = "HelloWorld"
                    text = "First item in list"
                    width = matchParent
                    height = wrapContent
                    paddingStart = 10.px
                    paddingEnd = 10.px
                    marginTop = 8.px
                    marginBottom = 8.px
                }
                view {
                    width = matchParent
                    height = 1.px
                    background = lightGrey
                    marginTop = 8.px
                    marginBottom = 8.px
                }
                view {
                    id = "ThirdView"
                    width = 370.px
                    height = 40.px
                    marginBottom = 40.px
                    background = lightGrey
                }

                textView {
                    id = "HelloWorld"
                    text = "First item in list"
                    width = matchParent
                    height = wrapContent
                    paddingStart = 10.px
                    paddingEnd = 10.px
                    marginTop = 8.px
                    marginBottom = 8.px
                }
                view {
                    width = matchParent
                    height = 1.px
                    background = lightGrey
                    marginTop = 8.px
                    marginBottom = 8.px
                }
                view {
                    id = "ThirdView"
                    width = 370.px
                    height = 40.px
                    marginBottom = 40.px
                    background = lightGrey
                }

                textView {
                    id = "HelloWorld"
                    text = "First item in list"
                    width = matchParent
                    height = wrapContent
                    paddingStart = 10.px
                    paddingEnd = 10.px
                    marginTop = 8.px
                    marginBottom = 8.px
                }
                view {
                    width = matchParent
                    height = 1.px
                    background = lightGrey
                    marginTop = 8.px
                    marginBottom = 8.px
                }

            }

            verticalLayout {
                id = "Vertical layout"
                background = Color.white
                setPadding(20.px)
                marginStart = 2.px
                width = weightOf(1)
                height = matchParent
                isScrollableVertically = true

                view {
                    id = "View1"
                    width = matchParent
                    height = 500.px
                    background = Color.rgb(0, 255, 0)
                }
                view {
                    id = "View2"
                    width = 500.px
                    height = 500.px
                    background = Color.rgb(0, 0, 255)
                }
                view {
                    id = "View3"
                    width = 500.px
                    height = 500.px
                    background = Color.rgb(255, 0, 0)
                }
            }
        }
    }
}

