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
import org.w3c.dom.HTMLInputElement

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 12/14/17.
 */
class ReportsView : DashboardPlainViewContent("Reports view") {
    override val plainPresenter = ReportsPresenter(this)

    val listPresenter = SalesmenListReportPresenter(this)

    private var salesmenList: LinearLayout? = null
    private var salesmenListLoadingImageView: ImageView? = null
    private var salesmenInListViewsList: ArrayList<SalesmanInListView> = arrayListOf()

    private var noSalesmanIsSelectedTextView: TextView? = null

    /*
    "Requests"
"Offers"
Approved offers
Orders to be delivered
Orders actually delivered
Amount to be collected
Amount actually collected
     */
    private var requestsTextView: TextView? = null
    private var offersTextView: TextView? = null
    private var ordersTextView: TextView? = null
    private var ordersToBeDeliveredTextView: TextView? = null
    private var ordersActuallyDeliveredTextView: TextView? = null
    private var paymentsNoToBeCollectedTextView: TextView? = null
    private var paymentsPriceToBeCollectedTextView: TextView? = null
    private var paymentsNoActuallyCollectedTextView: TextView? = null
    private var paymentsPriceActuallyCollectedTextView: TextView? = null

    private var reportsListLayout: LinearLayout? = null
    private var statsLoadingIndicator: ImageView? = null

    private var startDateInput: TextInput? = null
    private var endDateInput: TextInput? = null
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
                        startDateInput = textInput {
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
                        endDateInput = textInput {
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

                            onClick = {
                                var startDateString = (startDateInput?.element as? HTMLInputElement)?.value ?: ""
                                var endDateString = (endDateInput?.element as? HTMLInputElement)?.value ?: ""

                                if (startDateString.isNotEmpty()){
                                    val (year, month , day) = startDateString.split("-")
                                    startDateString = "$day-$month-$year"
                                }
                                if (endDateString.isNotEmpty()){
                                    val (year, month , day) = endDateString.split("-")
                                    endDateString = "$day-$month-$year"
                                }


                                plainPresenter.onDateRefreshed(
                                        startDateString.takeIf { it.isNotEmpty() },
                                        endDateString.takeIf { it.isNotEmpty() }
                                        )
                            }

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

                    statsLoadingIndicator = loadingIndicator()

                    noSalesmanIsSelectedTextView = textView {
                        text = "No salesman is selected"
                        width = matchParent
                        textAlign = TextView.TextAlign.Center
                        textSize = 20.px
                        textColor = DopaColors.separatorLight
                        isVisible = false
                    }


                    reportsListLayout = verticalLayout {
                        width = matchParent

                        requestsTextView = showIntReportEntry("Requests", true, plainPresenter)
                        offersTextView = showIntReportEntry("Offers", false, plainPresenter)
                        ordersTextView = showIntReportEntry("Orders (approved offers)", true, plainPresenter)
                        ordersToBeDeliveredTextView = showIntReportEntry("Orders to be delivered", false, plainPresenter)
                        ordersActuallyDeliveredTextView = showIntReportEntry("Orders actually delivered", true, plainPresenter)
                        showIntAndDoubleReportEntry("Amount to be collected", false, plainPresenter).apply {
                            paymentsNoToBeCollectedTextView = this.first
                            paymentsPriceToBeCollectedTextView = this.second
                        }
                        showIntAndDoubleReportEntry("Amount actually collected", true, plainPresenter).apply {
                            paymentsNoActuallyCollectedTextView = this.first
                            paymentsPriceActuallyCollectedTextView = this.second
                        }
                    }
                }
            }
        }
    }


    fun LinearLayout.showIntReportEntry(name: String, isWhite: Boolean, presenter: ReportsPresenter): TextView? {
        var valueTextView: TextView? = null
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

            valueTextView = textView {
                text = "0.0"
                textColor = DopaColors.main
                textSize = 18.px
                width = wrapContent
            }

        }
        return valueTextView
    }

    fun LinearLayout.showIntAndDoubleReportEntry(name: String, isWhite: Boolean, presenter: ReportsPresenter): Pair<TextView?, TextView?> {
        var numberTextView: TextView? = null
        var priceTextView: TextView? = null
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
                width = weightOf(5)
            }

            horizontalLayout {
                width = weightOf(1)
                justifyContent = JustifyContent.SpaceBetween
                priceTextView = textView {
                    text = "0.0"
                    textColor = DopaColors.main
                    textSize = 18.px
                    width = wrapContent
                }

                numberTextView = textView {
                    text = "0.0"
                    textColor = DopaColors.main
                    textSize = 18.px
                    width = wrapContent
                }
            }
        }

        return Pair(numberTextView, priceTextView)
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

        fun setSelected() {
            indicator?.background = DopaColors.main
        }

        fun setDeselected() {
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

    fun showNOSalesmanIsSelected() {
        noSalesmanIsSelectedTextView?.isVisible = true
        reportsListLayout?.isVisible = false
        statsLoadingIndicator?.isVisible = false
    }

    fun showSalesmanStatsInfo(stats: GetReportsResponseDto) {
        noSalesmanIsSelectedTextView?.isVisible = false
        statsLoadingIndicator?.isVisible = false
        reportsListLayout?.isVisible = true

//        requestsTextView
        offersTextView?.text = stats.noOfOffers.toString()
        ordersTextView?.text = stats.noOfOrders.toString()
        ordersToBeDeliveredTextView?.text = stats.noOfShouldBeDelivered.toString()
        ordersActuallyDeliveredTextView?.text = stats.noOfActuallyDelivered.toString()
        paymentsNoToBeCollectedTextView?.text = stats.noOfShouldBeCollected.toString()
        paymentsPriceToBeCollectedTextView?.text = "\$${stats.priceOfShouldBeCollected}"
        paymentsNoActuallyCollectedTextView?.text = stats.noOfActuallyCollected.toString()
        paymentsPriceActuallyCollectedTextView?.text = "\$${stats.priceOfActuallyCollected}"
    }

    fun showLoadingSalesmanStatsInfo() {
        statsLoadingIndicator?.isVisible = true
        reportsListLayout?.isVisible = false
        noSalesmanIsSelectedTextView?.isVisible = false
    }
}


