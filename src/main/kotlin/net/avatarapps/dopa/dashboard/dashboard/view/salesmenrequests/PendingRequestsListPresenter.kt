package net.avatarapps.dopa.dashboard.dashboard.view.salesmenrequests

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.drawable.Color
import net.avatarapps.kunafa.core.presenter.Presenter
import org.w3c.xhr.XMLHttpRequest

class PendingRequestsListPresenter(
        private val salesmenRequestsView: SalesmenRequestsView
) : Presenter() {
    var loadingImage: ImageView? = null
    var listView: LinearLayout? = null
    var noRequestsText: TextView? = null

    private var selectedIndex: Int? = null

    private var pendingRequests: Array<PendingRequest> = arrayOf()
    private var selectionIndicatorViews: ArrayList<View> = arrayListOf()

    override fun onViewCreated(view: View) {
        showLoadingImage()
        ServerCaller.getAllSalesmenRequests(
                onSuccess = { xmlHttpRequest ->
                    val jsonResponse = JSON.parse<ResponseDto>(xmlHttpRequest.responseText)
                    this.pendingRequests = jsonResponse.data.pendingRequests
                    showPendingRequests()

                },
                onError = {

                }
        )

    }

    private fun showPendingRequests() {
        if (pendingRequests.isNotEmpty()) {
            hideLoadingImage()
            listView?.clearAllChildren()
            selectionIndicatorViews.clear()
            this.pendingRequests.forEachIndexed { index, request ->
                val indicator = salesmenRequestsView.addPendingRequest(
                        index,
                        request,
                        listView,
                        request.salesmanDto.name,
                        request.pharmacyDs.nameEnglish)
                indicator?.let {
                    selectionIndicatorViews.add(it)
                }
            }
        } else {
            showNoRequestsText()
        }

    }

    private fun showLoadingImage() {
        loadingImage?.isVisible = true
        listView?.isVisible = false
        noRequestsText?.isVisible = false
    }

    private fun hideLoadingImage() {
        loadingImage?.isVisible = false
        listView?.isVisible = true
        noRequestsText?.isVisible = false
    }

    private fun showNoRequestsText() {
        noRequestsText?.isVisible = true
        loadingImage?.isVisible = false
        listView?.isVisible = false
    }


    fun onRequestSelected(index: Int) {
        selectedIndex = index
        selectionIndicatorViews.forEach {
            it.background = Color.transparent
        }
        selectionIndicatorViews[index].background = DopaColors.main
    }

    fun removeSelectedRequest() {
        selectedIndex?.let {
            val newArray = pendingRequests.filterIndexed { index, _ -> index != selectedIndex }
            pendingRequests = newArray.toTypedArray()
        }
        selectedIndex = null
        showPendingRequests()
    }

}