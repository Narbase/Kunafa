package net.avatarapps.modernweb
import net.avatarapps.modernweb.core.components.layout.LinearLayout.Orientation.horizontal
import net.avatarapps.modernweb.core.components.layout.LinearLayout.Orientation.vertical
import net.avatarapps.modernweb.core.components.linearLayout
import net.avatarapps.modernweb.core.components.page
import net.avatarapps.modernweb.core.components.view
import net.avatarapps.modernweb.core.dimensions.px
import net.avatarapps.modernweb.core.dimensions.wrapContent
import net.avatarapps.modernweb.core.drawable.Color

fun main(args: Array<String>) {
    page {
        linearLayout(horizontal) {
            background = Color.rgb(200, 200, 200)
            width = 1500.px
            height = 600.px
            setPadding(20.px)

            linearLayout(vertical) {
                id = "Linear layout"
                background = Color.rgb(220, 220, 220)
                width = wrapContent
                height = wrapContent
                setPadding(20.px)
                isScrollableVertically = true

                val rView = view {
                    width = 500.px
                    height = 200.px
                    background = Color.red
                }
                view {
                    width = 250.px
                    height = 200.px
                    background = Color.blue
                }

                view {
                    width = 100.px
                    height = 200.px
                    background = Color.rgb(255, 0, 255)
                }
            }

            linearLayout(vertical) {
                background = Color.rgb(220, 220, 220)
                width = 500.px
                height = 400.px
                setPadding(20.px)
                isScrollableVertically = true

                view {
                    width = 500.px
                    height = 500.px
                    background = Color.rgb(0,255,0)
                }
                view {
                    width = 500.px
                    height = 500.px
                    background = Color.rgb(0,0,255)
                }
                view {
                    width = 500.px
                    height = 500.px
                    background = Color.rgb(255,0,0)
                }
            }
        }
    }
}

