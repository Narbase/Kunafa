package com.narbase.kunafa.app

import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.components.Page.title
import com.narbase.kunafa.core.components.layout.Alignment
import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dependent.weightOf
import com.narbase.kunafa.core.dimensions.dependent.wrapContent
import com.narbase.kunafa.core.dimensions.independent.px
import com.narbase.kunafa.core.drawable.Color

fun main(args: Array<String>) {
    App().setup()
}

class App {

    fun setup() {

        page {
            title = "Kunafa Demo"
            verticalLayout {
                width = matchParent
                height = matchParent
                background = Color.rgb(240, 240, 240)
                verticalLayout {
                    width = matchParent
                    height = wrapContent
                    background = Color.rgb(36, 36, 36)
                    padding = 32.px
                    alignItems = Alignment.Center

                    imageView {
                        width = 120.px
                        img.src = "/public/img/kunafa.png"
                    }

                    textView {
                        text = "Kunafa"
                        textSize = 38.px
                        textColor = Color.white
                        span.style.fontFamily = "sans-serif"
                        marginTop = 18.px
                    }
                    textView {
                        text = "Demo app"
                        textSize = 18.px
                        textColor = Color.white
                        span.style.fontFamily = "sans-serif"
                        marginTop = 2.px
                    }

                }

                horizontalLayout {
                    width = matchParent
                    height = wrapContent
                    padding = 8.px

                    addCustomSeparator(backgroundColor = Color.rgb(183, 46, 69))
                    addCustomSeparator(backgroundColor = Color.rgb(36, 36, 36))
                    addCustomSeparator(backgroundColor = Color.rgb(183, 46, 69))

                }

                textView {
                    width = 600.px
                    text =
                            """Visit gitbub page to learn more about Kunafa,<br/>
                            Visit <a href="">Kunafa github page<a/>
                    """
                    textSize = 14.px
                    textColor = Color.rgb(120, 120, 120)
                    span.style.fontFamily = "sans-serif"
                    alignSelf = Alignment.Center
                    textAlign = TextView.TextAlign.Center
                }
            }
        }
    }

    private fun LinearLayout.addCustomSeparator(backgroundColor: Color) {

        view {
            width = weightOf(1)
            height = 1.px
            background = backgroundColor
            marginStart = 8.px
            marginEnd = 8.px
        }
    }
}

