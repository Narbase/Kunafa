package net.avatarapps.dopa.dashboard.dashboard.view.salesmen

import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View

class SalesmenPresenter : DashboardPlainPresenter() {

    var addSalesmanButton: TextView? = null
    var saveNewSalesmanButton: TextView? = null
    var cancelAddSalesmanButton: TextView? = null

    val salesmenListView = SalesmenListView(this)
    private val addSalesmanView = AddSalesmanView(this)

    override fun onViewCreated(view: View) {
        cancelAddSalesmanButton?.onClick = {
            mainViewContent?.content = salesmenListView
        }

        saveNewSalesmanButton?.onClick = {
            mainViewContent?.content = salesmenListView

        }

        addSalesmanButton?.onClick = {
            mainViewContent?.content = addSalesmanView

        }
    }


}