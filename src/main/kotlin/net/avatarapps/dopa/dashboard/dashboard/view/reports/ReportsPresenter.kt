package net.avatarapps.dopa.dashboard.dashboard.view.reports

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.SalesmanDs
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.addSalesman
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import kotlin.js.Json

class ReportsPresenter : DashboardPlainPresenter() {
    var salesmenListLoadingImageView: ImageView? = null
    var salesmenList: LinearLayout? = null

    override fun onViewCreated(view: View) {

        ServerCaller.getAllSalesmen(
                onSuccess = { xmlHttpRequest ->

                    if (xmlHttpRequest.status == 200.toShort()) {
                        salesmenList?.isVisible = true
                        salesmenListLoadingImageView?.isVisible = false

                        console.log(JSON.parse(xmlHttpRequest.responseText))
                        val salesmenResponse = JSON.parse<Json>(xmlHttpRequest.responseText).get("data") as? Json
                        val salesmen = salesmenResponse?.get("salesmen") as? Array<Json>

                        salesmen?.map {
                            val zonesIds = arrayListOf<Int>()
                            SalesmanDs(it["name"] as? String ?: "",
                                    it["username"] as? String ?: "",
                                    "",
                                    (it["zones"] as? Array<Int>)?.mapTo(zonesIds) { it } ?: arrayListOf(),
                                    it["phone"] as? String ?: "",
                                    it["approval"] as? Boolean ?: false,
                                    it["id"] as? Int)
                        }?.forEach {
                            salesmenList?.addReportSalesman(it, this)
                        }
                    } else {

                        salesmenList?.isVisible = false
                        salesmenListLoadingImageView?.isVisible = false
//                        noSalesmenTextView?.isVisible = true
//                        noSalesmenTextView?.text = "Unknown error. Refresh page."
                    }
                },
                onError = {
                    salesmenList?.isVisible = false
                    salesmenListLoadingImageView?.isVisible = false
//                    noSalesmenTextView?.isVisible = true
//                    noSalesmenTextView?.text = "No internet connection. Refresh page."
                }
        )

    }

    fun onSalesmanClicked(salesman: SalesmanDs) {

    }
}