package net.avatarapps.dopa.dashboard.dashboard.view.reports

import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.GetSalesmenDataResponseDto
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.SalesmanDs
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.presenter.Presenter

class SalesmenListReportPresenter(
        private val reportsView: ReportsView
) : Presenter() {
    override fun onViewCreated(view: View) {
        reportsView.setSalesmenLoadingImageVisible()

        ServerCaller.getAllSalesmen(
                onSuccess = { xmlHttpRequest ->

                    if (xmlHttpRequest.status == 200.toShort()) {
                        reportsView.addAllEntryToList(this)

                        reportsView.setSalesmenListVisible()

                        val salesmenResponse = JSON.parse<GetSalesmenDataResponseDto>(xmlHttpRequest.responseText)
                        val salesmen = salesmenResponse.data.salesmen

                        salesmen.map {
                            val zonesIds = arrayListOf<Int>()
                            SalesmanDs(it.name,
                                    it.username,
                                    "",
                                    it.zones.mapTo(zonesIds) { it },
                                    it.phone,
                                    it.approval,
                                    it.id)
                        }.forEachIndexed { index, salesmanDs ->
                            reportsView.addSalesmanToList(index + 1, salesmanDs, this)
                        }
                    } else {

//                        salesmenList?.isVisible = false
//                        salesmenListLoadingImageView?.isVisible = false
//                        noSalesmenTextView?.isVisible = true
//                        noSalesmenTextView?.text = "Unknown error. Refresh page."
                    }
                },
                onError = {
//                    salesmenList?.isVisible = false
//                    salesmenListLoadingImageView?.isVisible = false
//                    noSalesmenTextView?.isVisible = true
//                    noSalesmenTextView?.text = "No internet connection. Refresh page."
                }
        )

    }

    fun onSalesmanSelected(position: Int, salesman: SalesmanDs?) {
        reportsView.setSalesmanSelectedAt(position)
        if (salesman == null) {
        reportsView.showAllSalesmenInfo()
        } else {
            reportsView.showSalesmanInfo(salesman)
        }
    }
}