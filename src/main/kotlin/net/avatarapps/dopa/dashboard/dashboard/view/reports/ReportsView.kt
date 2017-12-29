package net.avatarapps.dopa.dashboard.dashboard.view.reports

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.SalesmanDs
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.makeClickable
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.layout.JustifyContent
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.weightOf
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 12/14/17.
 */
class ReportsView : DashboardPlainViewContent("Reports view") {
    override val plainPresenter = ReportsPresenter()

    override var pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            horizontalLayout {
                width = matchParent
                height = matchParent



                plainPresenter.salesmenList = verticalLayout {
                    width = weightOf(1)
                    height = matchParent
                    paddingEnd = 24.px
                    isScrollableVertically = true
                    textView {
                        text = "Salesmen"
                        textSize = 20.px
                        textColor = DopaColors.text
                    }

//                    addSalesman(SalesmanDs("All", "", "", arrayListOf(), "0123123123"), plainPresenter)
//                    addSalesman(SalesmanDs("Name", "", "", arrayListOf(), "0123123123"), plainPresenter)
//                    addSalesman(SalesmanDs("Name", "", "", arrayListOf(), "0123123123"), plainPresenter)
//                    addSalesman(SalesmanDs("Name", "", "", arrayListOf(), "0123123123"), plainPresenter)
//                    addSalesman(SalesmanDs("Name", "", "", arrayListOf(), "0123123123"), plainPresenter)
//                    addSalesman(SalesmanDs("Name", "", "", arrayListOf(), "0123123123"), plainPresenter)
//                    addSalesman(SalesmanDs("Name", "", "", arrayListOf(), "0123123123"), plainPresenter)
                }

                verticalLayout {
                    width = weightOf(3)
                    height = matchParent

                    horizontalLayout {
                        width = matchParent
                        alignItems = Alignment.Center
                        justifyContent = JustifyContent.SpaceBetween
                        marginBottom = 16.px

                        textView {
                            text = "From"
                            width = weightOf(1)
                            textAlign = TextView.TextAlign.Right
                            textSize = 20.px
                            marginStart = 16.px
                            marginEnd = 16.px
                        }
                        textInput {
                            type = "date"
                            width = weightOf(2)
                            textSize = 20.px
                            marginStart = 16.px
                            marginEnd = 16.px
                        }

                        textView {
                            text = "To"
                            width = weightOf(1)
                            textAlign = TextView.TextAlign.Right
                            textSize = 20.px
                            marginStart = 16.px
                            marginEnd = 16.px
                        }
                        textInput {
                            type = "date"
                            width = weightOf(2)
                            textSize = 20.px
                            marginStart = 16.px
                            marginEnd = 16.px

                        }

                        textView {
                            text = "Refresh"
                            width = weightOf(2)
                            textAlign = TextView.TextAlign.Center
                            background = DopaColors.greenLight
                            textSize = 20.px
                            marginStart = 16.px
                            marginEnd = 16.px
                            paddingTop = 4.px
                            paddingBottom = 4.px
                            textColor = Color.white

                            element.onmouseover = {
                                background = DopaColors.separatorLight
                                element.style.cursor = "pointer"
                                asDynamic()
                            }
                            element.onmouseleave = {
                                background = DopaColors.greenLight
                                element.style.cursor = ""
                                asDynamic()

                            }
                        }
                    }

                    showReportEntry("Requests", true, plainPresenter)
                    showReportEntry("Offers", false, plainPresenter)
                    showReportEntry("Approved offers", true, plainPresenter)
                    showReportEntry("Orders to be delivered", false, plainPresenter)
                    showReportEntry("Orders actually delivered", true, plainPresenter)
                    showReportEntry("Amount to be collected", false, plainPresenter)
                    showReportEntry("Amount actually collected", true, plainPresenter)


                    /**
                     * No. of requests.
                     * No. of offers.
                     * No. of orders (approved offers)
                     * No. of should be delivered orders ($ in () )
                     * No. of actual delivered ($ in () ) .
                     * No. of should be collected (price in parenthesis) (order time can be outside this period but collection within this period).
                     * No. of actually collected ($ in () ).
                     */
                }
            }
        }
    }



    fun LinearLayout.showReportEntry(name: String, isWhite: Boolean, presenter: ReportsPresenter) {

        horizontalLayout {
            width = matchParent
            marginStart = 8.px
            marginEnd = 8.px
            padding = 16.px
            background = if (isWhite) Color.white else DopaColors.backgroundLight
            justifyContent = JustifyContent.SpaceBetween


            textView {
                text = name
                textColor = DopaColors.main
                textSize = 18.px
                width = weightOf(1)
            }

            textView {
                text = "0.0"
                textColor = DopaColors.main
                textSize = 18.px
                width = wrapContent
            }

        }
    }

}
fun LinearLayout.addReportSalesman(salesman: SalesmanDs, presenter: ReportsPresenter) {
    verticalLayout {
        width = matchParent
        horizontalLayout {
            width = matchParent
            marginTop = 16.px
            padding = 16.px
            background = Color.white
            justifyContent = JustifyContent.SpaceBetween

            element.onmouseover = {
                background = DopaColors.separatorLight
                element.style.cursor = "pointer"
                asDynamic()
            }
            element.onmouseleave = {
                background = Color.white
                element.style.cursor = ""
                asDynamic()

            }

            textView {
                text = salesman.name
                textColor = DopaColors.main
                textSize = 18.px
            }

        }

        view {
            width = matchParent
            height = 2.px
            background = DopaColors.separatorLight
        }
    }
}
