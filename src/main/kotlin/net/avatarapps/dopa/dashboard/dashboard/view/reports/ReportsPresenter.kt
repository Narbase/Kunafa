package net.avatarapps.dopa.dashboard.dashboard.view.reports

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.GetSalesmenDataResponseDto
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.SalesmanDs
import net.avatarapps.dopa.dashboard.dashboard.view.salesmen.addSalesman
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import kotlin.js.Json

class ReportsPresenter(
        val reportsView: ReportsView) : DashboardPlainPresenter() {
    var salesmenListLoadingImageView: ImageView? = null
    var salesmenList: LinearLayout? = null

    private var startDate: String? = null
    private var endDate: String? = null

    override fun onViewCreated(view: View) {
        reportsView.showNOSalesmanIsSelected()
    }

    private var salesman: SalesmanDs? = null

    fun showAllSalesmenInfo() {
        showSalesmanInfo(null)
    }

    fun showSalesmanInfo(salesman: SalesmanDs?) {
        this.salesman = salesman
        refreshStatsView()
    }

    private fun refreshStatsView() {
        reportsView.showLoadingSalesmanStatsInfo()
        ServerCaller.getReportStats(GetReportsRequestDto(this.salesman?.id, startDate, endDate),
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        val response = JSON.parse<GetReportsDataResponseDto>(xmlHttpRequest.responseText)
                        reportsView.showSalesmanStatsInfo(response.data)

                    }
                },
                onError = {

                }
        )
    }

    fun onDateRefreshed(startDate: String?, endDate: String?) {
        this.startDate = startDate
        this.endDate = endDate
        refreshStatsView()
    }
}


data class GetReportsRequestDto(
        val salesmanId: Int?,
        val startDate: String?,
        val endDate: String?
)

data class GetReportsDataResponseDto(
        val data: GetReportsResponseDto
)

data class GetReportsResponseDto(
        val noOfRequests: Int,
        val noOfOffers: Int,
        val noOfOrders: Int,
        val noOfShouldBeDelivered: Int,
        val noOfActuallyDelivered: Int,
        val noOfShouldBeCollected: Int,
        val noOfActuallyCollected: Int,
        val priceOfShouldBeCollected: Double,
        val priceOfActuallyCollected: Double
)