package net.avatarapps.dopa.dashboard.dashboard.view.reports

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.SalesmanDs
import net.avatarapps.dopa.dashboard.dashboard.view.smartoffers.loadingIndicator
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

    val listPresenter = SalesmenListReportPresenter(this)

    private var salesmenList: LinearLayout? = null
    private var salesmenListLoadingImageView: ImageView? = null
    private var salesmenInListViewsList : ArrayList<SalesmanInListView> = arrayListOf()

    override var pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            horizontalLayout {
                width = matchParent
                height = matchParent

                verticalLayout {
                    width = weightOf(1)
                    height = matchParent
                    paddingEnd = 24.px
                    isScrollableVertically = true
                    presenter = listPresenter
                    textView {
                        text = "Salesmen"
                        textSize = 20.px
                        textColor = DopaColors.text
                    }

                    salesmenListLoadingImageView = loadingIndicator()

                    salesmenList = verticalLayout {
                        height = matchParent
                        width = matchParent

                    }
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

    fun setSalesmenListVisible() {
        salesmenList?.isVisible = true
        salesmenListLoadingImageView?.isVisible = false
    }

    fun setSalesmenLoadingImageVisible() {
        salesmenList?.isVisible = true
        salesmenListLoadingImageView?.isVisible = false
    }

    fun addSalesmanToList(position: Int, salesman: SalesmanDs, salesmenListReportPresenter: SalesmenListReportPresenter) {
        salesmenList?.addReportSalesman(position, salesman, salesmenListReportPresenter)
    }

    fun addAllEntryToList(salesmenListReportPresenter: SalesmenListReportPresenter) {
        salesmenList?.addReportSalesman(0, null, salesmenListReportPresenter)
    }

    private fun LinearLayout.addReportSalesman(position: Int, salesman: SalesmanDs?, presenter: SalesmenListReportPresenter) {
        val view = SalesmanInListView()
        verticalLayout {
            width = matchParent
            marginTop = 16.px
            horizontalLayout {
                width = matchParent
                background = Color.white
                onClick = { presenter.onSalesmanSelected(position, salesman) }

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

                view.indicator = view {
                    height = matchParent
                    width = 8.px
                    background = Color.transparent
                }

                textView {
                    padding = 16.px
                    text = salesman?.name ?: "All"
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

        salesmenInListViewsList.add(position, view)
    }

    class SalesmanInListView {
        var indicator: View? = null

        fun setSelected(){
            indicator?.background = DopaColors.main
        }
        fun setDeselected(){
            indicator?.background = Color.transparent
        }
    }


    fun setSalesmanSelectedAt(position: Int) {
        salesmenInListViewsList.forEach {
            it.setDeselected()
        }

        try {
            salesmenInListViewsList[position].setSelected()
        } catch (e: Exception) {

        }

    }

    fun showSalesmanInfo(salesman: SalesmanDs) {
        plainPresenter.showSalesmanInfo(salesman)

    }

    fun showAllSalesmenInfo() {
        plainPresenter.showAllSalesmenInfo()
    }
}


