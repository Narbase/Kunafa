package net.avatarapps.modernweb
import net.avatarapps.modernweb.core.components.layout.LinearLayout.Orientation.horizontal
import net.avatarapps.modernweb.core.components.layout.LinearLayout.Orientation.vertical
import net.avatarapps.modernweb.core.components.linearLayout
import net.avatarapps.modernweb.core.components.page
import net.avatarapps.modernweb.core.components.view
import net.avatarapps.modernweb.core.dimensions.px
import net.avatarapps.modernweb.core.drawable.Color

fun main(args: Array<String>) {

    page {
        linearLayout(horizontal) {
            background = Color(200, 200, 200)
            width = 1500.px
            height = 600.px
            setPadding(20.px)

            linearLayout(vertical) {
                background = Color(220, 220, 220)
                width = 300.px
                height = 400.px
                setPadding(20.px)
                isScrollableVertically = true

                val redView = view {
                    width = 300.px
                    height = 500.px
                    background = Color(red = 255, green = 0, blue = 0)
                }
                view {
                    width = 300.px
                    height = 500.px
                    background = Color(red = 0, green = 0, blue = 255)
                }
            }

            linearLayout(vertical) {
                background = Color(220, 220, 220)
                width = 500.px
                height = 400.px
                setPadding(20.px)
                isScrollableVertically = true

                view {
                    width = 500.px
                    height = 500.px
                    background = Color(red = 0, green = 255, blue = 0)
                }
                view {
                    width = 500.px
                    height = 500.px
                    background = Color(red = 0, green = 0, blue = 255)
                }
                view {
                    width = 500.px
                    height = 500.px
                    background = Color(red = 255, green = 0, blue = 0)
                }
            }
        }
    }
}

