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

class ReportsPresenter : DashboardPlainPresenter() {
    var salesmenListLoadingImageView: ImageView? = null
    var salesmenList: LinearLayout? = null

    override fun onViewCreated(view: View) {


    }

    fun showSalesmanInfo(salesman: SalesmanDs) {

    }

    fun showAllSalesmenInfo() {

    }
}