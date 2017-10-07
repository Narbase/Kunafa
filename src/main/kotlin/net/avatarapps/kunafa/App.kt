package net.avatarapps.kunafa

import net.avatarapps.kunafa.core.components.horizontalLayout
import net.avatarapps.kunafa.core.components.page
import net.avatarapps.kunafa.core.components.verticalLayout
import net.avatarapps.kunafa.core.components.view
import net.avatarapps.kunafa.core.dimensions.matchParent
import net.avatarapps.kunafa.core.dimensions.px
import net.avatarapps.kunafa.core.dimensions.wrapContent
import net.avatarapps.kunafa.core.drawable.Color

fun main(args: Array<String>) {
    page {
        horizontalLayout {
            id = "horizontalLayout"
            background = Color.rgb(200, 200, 200)
            width = 1500.px
            height = matchParent
            setPadding(20.px)
            verticalLayout {
                id = "Vertical Layout"
                background = Color.rgb(220, 220, 220)
                width = wrapContent
                height = matchParent
                setPadding(20.px)
                isScrollableVertically = true
                val redView = view {
                    id = "redView"
                    width = 120.px
                    height = 500.px
                    background = Color.red
                }
                view {
                    id = "anotherView"
                    width = 100.px
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

            verticalLayout {
                id = "Vertical layout"
                background = Color.rgb(220, 220, 220)
                width = 800.px
                height = 600.px
                setPadding(20.px)
                marginStart = 30.px
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

