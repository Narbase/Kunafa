package net.avatarapps.dopa.dashboard.dashboard.view.salesmenrequests

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.dopa.dashboard.dashboard.view.smartoffers.loadingIndicator
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

    override var pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
//            textView {
//                text = "No requests yet."
//                textColor = DopaColors.separatorLight
//                textSize = 24.px
//                width = matchParent
//                textAlign = TextView.TextAlign.Center
//                marginTop = 16.px
//                isVisible = false
//            }

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
                    pendingRequestsListPresenter.loadingImage = loadingIndicator()

                    pendingRequestsListPresenter.listView = horizontalLayout {
                        width = matchParent
                        height = matchParent

                    }
                }

                verticalLayout {
                    width = weightOf(3)
                    marginStart = 8.px

                    textView {
                        text = "Details"
                        textColor = DopaColors.text
                        textSize = 18.px
                        marginBottom = 8.px
                    }

                    controlButtons()

                    subTitle("Salesman info")
                    keyValueEntry("Name", "Omar Ali")
                    keyValueEntry("Phone", "0129232844")
                    separator()

                    subTitle("Pharmacy info")
                    keyValueEntry("Name", "Alkarawan - الكروان")
                    keyValueEntry("Location", "لفة شمبات الاراضي شمال")
                    keyValueEntry("Phone number", "09121312121 - 0912131298")
                    keyValueEntry("Pharmacist name", "Omer Ali")
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
                            orderItem("50", "343 SDG", "33", "1", "Panadol", "01-04-2018")
                            orderItem("50", "343 SDG", "33", "1", "Panadol", "01-04-2018")
                            orderItem("50", "343 SDG", "33", "1", "Panadol", "01-04-2018")

                        }

                        verticalLayout {
                            width = weightOf(1)
                            paddingStart = 8.px
                            paddingEnd = 8.px
                            subTitle("Payments")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")
                            paymentItem("01-04-2018", "50")

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

    private fun LinearLayout.controlButtons() {
        horizontalLayout {
            width = matchParent
            textView {
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

            textView {
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
    fun LinearLayout.orderItem(
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

    /*
    {
            "date": "10-02-2018",
            "price": 100
        }
     */
    fun LinearLayout.paymentItem(
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
            listView: LinearLayout?,
            salesmanName: String,
            pharmacyName: String
    ) {
        if (listView == null) return
        with(listView) {
            verticalLayout {
                width = matchParent
                marginTop = 8.px
                background = Color.white
                height = wrapContent
                horizontalLayout {
                    width = matchParent
                    height = wrapContent
                    alignItems = Alignment.Stretch
                    val selector = view {
                        width = 8.px
                        height = matchParent
//                    alignSelf = Alignment.Stretch
                        background = Color.transparent
                    }

                    verticalLayout {
                        padding = 12.px
                        width = matchParent
                        height = wrapContent
                        justifyContent = JustifyContent.SpaceBetween

                        element.onmouseover = {
                            background = DopaColors.separatorLight
                            element.style.cursor = "pointer"
                            selector.background = DopaColors.main
                            asDynamic()
                        }
                        element.onmouseleave = {
                            background = Color.white
                            element.style.cursor = ""
                            selector.background = Color.transparent
                            asDynamic()

                        }

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
    }
}
