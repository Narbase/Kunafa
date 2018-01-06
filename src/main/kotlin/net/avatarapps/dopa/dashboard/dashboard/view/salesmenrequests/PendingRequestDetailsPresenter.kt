package net.avatarapps.dopa.dashboard.dashboard.view.salesmenrequests

import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.presenter.Presenter
import org.w3c.xhr.XMLHttpRequest

class PendingRequestDetailsPresenter(
        private val salesmenRequestsView: SalesmenRequestsView
) : Presenter() {
    var noRequestSelectedText: TextView? = null
    var detailsView: LinearLayout? = null

    override fun onViewCreated(view: View) {
        showNoRequestIsSelectedText()
    }

    private fun showNoRequestIsSelectedText() {
        noRequestSelectedText?.isVisible = true
        detailsView?.isVisible = false
    }

    fun onRequestSelected(request: PendingRequest) {
        makeRequestDetailsVisible()
        salesmenRequestsView.showRequest(request)
    }

    private fun makeRequestDetailsVisible() {
        noRequestSelectedText?.isVisible = false
        detailsView?.isVisible = true

    }


}