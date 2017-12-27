package net.avatarapps.dopa.dashboard.dashboard.view.salesmen

import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View

class SalesmenPresenter : DashboardPlainPresenter() {

    var addSalesmanButton: TextView? = null
    var saveNewSalesmanButton: TextView? = null
    var cancelAddSalesmanButton: TextView? = null
    var noSalesmenTextView: TextView? = null
    var salesmenListLoadingImageView: ImageView? = null

    val salesmenListView = SalesmenListView(this)
    private val addSalesmanView = AddSalesmanView(this)

    override fun onViewCreated(view: View) {
        getAndShowSalesmen()

    }

    fun onAddSalesmanButtonClicked() {
        mainViewContent?.content = addSalesmanView
    }

    fun onSaveNewSalesmanButtonClicked() {
        mainViewContent?.content = salesmenListView
    }

    fun onCancelAddSalesmanButton() {
        mainViewContent?.content = salesmenListView

    }

    private fun getAndShowSalesmen() {
        mainViewContent?.content = salesmenListView

        ServerCaller.getAllSalesmen(
                onSuccess = { xmlHttpRequest ->
                    console.log(JSON.parse(xmlHttpRequest.responseText))
//                    val
                },
                onError = {
                    println("Failed to get salesmen")
                }
        )

    }



}


class GetSalesmenResponseDto(
        val salesmen: ArrayList<SalesmanInListDto>
)

class SalesmanInListDto(
        val id: Int?,

        val name: String,

        val username: String,

        val phone: String,

        val zones: List<Int>,

        val approval: Boolean
)