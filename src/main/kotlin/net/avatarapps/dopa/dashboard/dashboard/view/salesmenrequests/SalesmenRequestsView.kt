package net.avatarapps.dopa.dashboard.dashboard.view.salesmenrequests

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.dopa.dashboard.dashboard.view.smartoffers.loadingIndicator
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.Areas
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.District
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.Neighbourhood
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.State
import net.avatarapps.dopa.dashboard.dashboard.view.zones.makeClickable
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


class SalesmenRequestsView : DashboardPlainViewContent("Salesmen requests") {
    override val plainPresenter = SalesmenRequestsPresenter()

    val pendingRequestsListPresenter = PendingRequestsListPresenter(this)
    val pendingRequestDetailsPresenter = PendingRequestDetailsPresenter(this)

    private var salesmanView: LinearLayout? = null
    private var pharmacyView: LinearLayout? = null
    private var orderItemsView: LinearLayout? = null
    private var paymentsView: LinearLayout? = null
    private var rejectRequestTextView: TextView? = null
    private var approveRequestTextView: TextView? = null


    override var pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {

            horizontalLayout {
                width = matchParent
                height = matchParent

                verticalLayout {
                    width = weightOf(1)
                    height = matchParent
                    presenter = pendingRequestsListPresenter

                    textView {
                        text = "Pending requests"
                        textColor = DopaColors.text
                        textSize = 18.px
                    }

                    pendingRequestsListPresenter.noRequestsText = textView {
                        text = "No requests."
                        textColor = DopaColors.separatorLight
                        textSize = 24.px
                        width = matchParent
                        textAlign = TextView.TextAlign.Center
                        marginTop = 16.px
                        isVisible = false
                    }
                    pendingRequestsListPresenter.loadingImage = loadingIndicator()

                    pendingRequestsListPresenter.listView = verticalLayout {
                        width = matchParent
                        height = matchParent

                    }
                }

                verticalLayout {
                    width = weightOf(3)
                    marginStart = 8.px
                    presenter = pendingRequestDetailsPresenter

                    textView {
                        text = "Details"
                        textColor = DopaColors.text
                        textSize = 18.px
                        marginBottom = 8.px
                    }

                    pendingRequestDetailsPresenter.noRequestSelectedText = textView {
                        text = "No request is selected."
                        textColor = DopaColors.separatorLight
                        textSize = 24.px
                        width = matchParent
                        textAlign = TextView.TextAlign.Center
                        marginTop = 16.px
                        isVisible = false
                    }

                    pendingRequestDetailsPresenter.detailsView = verticalLayout {
                        width = matchParent
                        height = matchParent
                        controlButtons()

                        subTitle("Salesman info")
                        salesmanView = verticalLayout {
                            width = matchParent
                            height = matchParent
                        }
                        separator()

                        subTitle("Pharmacy info")

                        pharmacyView = verticalLayout {
                            width = matchParent
                            height = matchParent

                        }
                        separator()

                        subTitle("Request Details")
                        keyValueEntry("Delivery date", "12-17-2018")

                        horizontalLayout {
                            width = matchParent
                            height = wrapContent

                            verticalLayout {
                                width = weightOf(1)
                                paddingStart = 8.px
                                paddingEnd = 8.px
                                subTitle("Order items")
                                orderItemsView = verticalLayout {
                                    width = matchParent
                                    height = wrapContent

                                }
                            }

                            verticalLayout {
                                width = weightOf(1)
                                paddingStart = 8.px
                                paddingEnd = 8.px
                                subTitle("Payments")
                                paymentsView = verticalLayout {
                                    width = matchParent
                                    height = wrapContent
                                }

                            }
                        }

                        /*
                         {
                                "amount": 50,
                                "price": 343,
                                "bonus": 33,
                                "discount": 1,
                                "productId": 1,
                                "productName": "Panadol",
                                "expirationDate": "01-04-2018"
                            }
                         */
                    }

                }
            }
        }
    }

    private fun LinearLayout.displayPharmacyInfo(name: String, locationDescripton: String, phone: String, pharmacistName: String) {
        clearAllChildren()
        keyValueEntry("Name", name)
        keyValueEntry("Location", locationDescripton)
        keyValueEntry("Phone number", phone)
        keyValueEntry("Pharmacist name", pharmacistName)
    }

    private fun LinearLayout.displaySalesmanInfo(name: String, phone: String) {
        clearAllChildren()
        keyValueEntry("Name", name)
        keyValueEntry("Phone", phone)
    }


    private fun LinearLayout.controlButtons() {
        horizontalLayout {
            width = matchParent
            rejectRequestTextView = textView {
                padding = 8.px
                width = weightOf(1)
                height = wrapContent
                textColor = Color.white
                text = "Reject"
                background = DopaColors.redLight

                makeClickable(DopaColors.redLight)
                width = wrapContent
                marginEnd = 18.px
                textAlign = TextView.TextAlign.Center
                //                            onClick = { salesmenPresenter.onCancelAddSalesmanButton() }
            }

            approveRequestTextView = textView {
                padding = 8.px
                width = weightOf(1)
                height = wrapContent
                textColor = Color.white
                text = "Approve request"
                background = DopaColors.greenLight
                makeClickable(DopaColors.greenLight)
                width = wrapContent
                textAlign = TextView.TextAlign.Center
                //                            onClick = { salesmenPresenter.onSaveNewSalesmanButtonClicked() }
            }

        }
    }

    private fun LinearLayout.separator() {
        view {
            width = matchParent
            height = 1.px
            background = DopaColors.separatorLight
            marginTop = 16.px
        }
    }

    private fun LinearLayout.subTitle(title: String) {
        textView {
            text = title
            textColor = DopaColors.mainDark
            textSize = 18.px
            marginTop = 16.px
        }
    }

    private fun LinearLayout.keyValueEntry(key: String, value: String) {
        horizontalLayout {
            marginTop = 4.px
            marginBottom = 4.px
            width = matchParent
            height = wrapContent
            textView {
                text = "$key:"
                textColor = DopaColors.mainLight
                textSize = 16.px
                marginEnd = 16.px
            }

            textView {
                text = value
                textColor = DopaColors.mainDark
                textSize = 16.px
            }
        }
    }

    private fun LinearLayout.orderItem(
            amount: String,
            price: String,
            bonus: String,
            discount: String,
            productName: String,
            expirationDate: String
    ) {
        verticalLayout {
            width = matchParent
            marginTop = 4.px
            marginBottom = 4.px
            background = Color.white
            height = wrapContent
            horizontalLayout {
                width = matchParent
                height = wrapContent
                alignItems = Alignment.Stretch

                verticalLayout {
                    padding = 12.px
                    width = matchParent
                    height = wrapContent
                    justifyContent = JustifyContent.SpaceBetween

                    keyValueEntry("Amount", amount)
                    keyValueEntry("Price", price)
                    keyValueEntry("Bonus", bonus)
                    keyValueEntry("Discount", discount)
                    keyValueEntry("Product name", productName)
                    keyValueEntry("Expiration date", expirationDate)

                }

            }

            view {
                width = matchParent
                height = 2.px
                background = DopaColors.separatorLight
            }
        }
    }

    private fun LinearLayout.paymentItem(
            date: String,
            price: String
    ) {
        verticalLayout {
            width = matchParent
            marginTop = 4.px
            marginBottom = 4.px
            background = Color.white
            height = wrapContent
            horizontalLayout {
                width = matchParent
                height = wrapContent
                alignItems = Alignment.Stretch

                verticalLayout {
                    padding = 12.px
                    width = matchParent
                    height = wrapContent
                    justifyContent = JustifyContent.SpaceBetween

                    keyValueEntry("Date", date)
                    keyValueEntry("Price", price)

                }

            }

            view {
                width = matchParent
                height = 2.px
                background = DopaColors.separatorLight
            }
        }
    }



    fun addPendingRequest(
            index: Int,
            request: PendingRequest,
            listView: LinearLayout?,
            salesmanName: String,
            pharmacyName: String
    ): View? {
        if (listView == null) return null
        var indicator: View? = null
        with(listView) {
            verticalLayout {
                width = matchParent
                marginTop = 8.px
                background = Color.white
                height = wrapContent

                onClick = {
                    pendingRequestDetailsPresenter.onRequestSelected(request)
                    pendingRequestsListPresenter.onRequestSelected(index)
                }

                horizontalLayout {
                    width = matchParent
                    height = wrapContent
                    alignItems = Alignment.Stretch
                    indicator = view {
                        width = 8.px
                        height = matchParent
                        background = Color.transparent
                    }

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
                    verticalLayout {
                        padding = 12.px
                        width = matchParent
                        height = wrapContent
                        justifyContent = JustifyContent.SpaceBetween


                        textView {
                            text = "By: $salesmanName"
                            textColor = DopaColors.mainDark
                            textSize = 18.px
                            height = wrapContent
                        }

                        textView {
                            text = "Pharmacy: $pharmacyName"
                            textColor = DopaColors.mainLight
                            marginTop = 8.px
                            textSize = 16.px
                            height = wrapContent
                        }
                    }
                }

                view {
                    width = matchParent
                    height = 2.px
                    background = DopaColors.separatorLight
                }

            }
        }
        return indicator
    }

    fun showRequest(request: PendingRequest) {
        salesmanView?.displaySalesmanInfo(request.salesmanDto.name, request.salesmanDto.phone)
        with(request.pharmacyDs) {
            var pharmacyNeighbourhood: Neighbourhood? = null
            var pharmacyDistrict: District? = null
            var pharmacyState: State? = null
            Areas.states.forEach { state ->
                state.districts.forEach { district ->
                    district.neighbourhoods.forEach { neighbourhood ->
                        if (neighbourhood.id == neighborhoodId) {
                            pharmacyNeighbourhood = neighbourhood
                            pharmacyDistrict = district
                            pharmacyState = state
                        }
                    }
                }
            }

            pharmacyView?.displayPharmacyInfo(
                    nameEnglish,
                    locationDescripton = "${pharmacyState?.nameAr ?: ""} " +
                            "${pharmacyDistrict?.nameAr ?: ""} " +
                            "${pharmacyNeighbourhood?.nameAr ?: ""} " +
                            locationDescription,
                    phone = "$firstPhone - $secondPhone",
                    pharmacistName = pharmacistName)
        }
        orderItemsView?.clearAllChildren()
        request.request.orderItemsList.forEach {
            orderItemsView?.orderItem(
                    it.amount.toString(),
                    "${it.price} SDG",
                    it.bonus.toString(),
                    it.discount.toString(),
                    it.productName,
                    it.expirationDate)
        }

        paymentsView?.clearAllChildren()
        request.request.payments.forEach {
            paymentsView?.paymentItem(it.date, "${it.price} SDG")
        }

        approveRequestTextView?.onClick = {
            pendingRequestDetailsPresenter.onRequestUpdated(
                    requestId = request.request.id,
                    isApproved = true)
        }

        rejectRequestTextView?.onClick = {
            pendingRequestDetailsPresenter.onRequestUpdated(
                    requestId = request.request.id,
                    isApproved = false)
        }
    }

    fun removeSelectedRequest() {
        pendingRequestsListPresenter.removeSelectedRequest()
    }

}
