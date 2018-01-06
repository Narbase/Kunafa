package net.avatarapps.dopa.dashboard.dashboard.view.salesmenrequests

import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.presenter.Presenter
import org.w3c.xhr.XMLHttpRequest

class PendingRequestsListPresenter(
        private val salesmenRequestsView: SalesmenRequestsView
) : Presenter() {
    var loadingImage: ImageView? = null
    var listView: LinearLayout? = null

    private var pendingRequests: Array<PendingRequest> = arrayOf()

    override fun onViewCreated(view: View) {
        showLoadingImage()
        ServerCaller.getAllSalesmenRequests(
                onSuccess = { xmlHttpRequest ->
                    val jsonResponse = JSON.parse<ResponseDto>(xmlHttpRequest.responseText)
                    this.pendingRequests = jsonResponse.data.pendingRequests
                    hideLoadingImage()
                    this.pendingRequests.forEach {
                        salesmenRequestsView.addPendingRequest(
                                listView,
                                it.salesmanDto.name,
                                it.pharmacyDs.nameEnglish)
                    }
                },
                onError = {

                }
        )

    }

    private fun showLoadingImage() {
        loadingImage?.isVisible = true
        listView?.isVisible = false
    }

    private fun hideLoadingImage() {
        loadingImage?.isVisible = false
        listView?.isVisible = true
    }

}