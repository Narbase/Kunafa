package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.layout.JustifyContent
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color

class DrugsView : DashboardPlainViewContent("Drugs Excel sheet") {
    override val plainPresenter = object : DashboardPlainPresenter() {
        override fun onViewCreated(view: View) {

        }
    }
    override var pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            verticalLayout {
                id = "Vertical layout"
                padding = 20.px
                marginStart = 0.px
                width = matchParent
                height = matchParent
                isScrollableVertically = true
                alignItems = Alignment.Center

                horizontalLayout {
                    padding = 16.px
                    justifyContent = JustifyContent.Center

                    verticalLayout {
                        padding = 16.px
                        background = Color.white
                        alignItems = Alignment.Center
                        marginEnd = 8.px

                        imageView {
                            height = 120.px
                            img.src = "http://www.oogazone.com/wp-content/uploads/best-upload-images.jpg"
                        }

                        textView {
                            text = "Upload excel sheet"
                            width = matchParent
                            height = wrapContent
                            paddingStart = 10.px
                            paddingEnd = 10.px
                            marginTop = 8.px
                            marginBottom = 8.px
                            textAlign = TextView.TextAlign.Center
                            textSize = 24.px
                            textColor = Color.rgb(51, 153, 219)
                        }
                    }

                    verticalLayout {
                        padding = 16.px
                        background = Color.white
                        alignItems = Alignment.Center
                        marginStart = 8.px

                        imageView {
                            height = 120.px
                            img.src = "https://sbi.hki-jena.de/cassis/img/download.png"
                        }

                        textView {
                            text = "Download excel sheet"
                            width = matchParent
                            height = wrapContent
                            paddingStart = 10.px
                            paddingEnd = 10.px
                            marginTop = 8.px
                            marginBottom = 8.px
                            textAlign = TextView.TextAlign.Center
                            textSize = 24.px
                            textColor = Color.rgb(51, 153, 219)
                        }

                    }


                }


            }

        }
    }
}
