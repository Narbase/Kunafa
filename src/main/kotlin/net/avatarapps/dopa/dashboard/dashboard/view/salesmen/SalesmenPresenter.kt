package net.avatarapps.dopa.dashboard.dashboard.view.salesmen

import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.TextInput
import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import kotlin.js.Json

class SalesmenPresenter : DashboardPlainPresenter() {

    var addSalesmanButton: TextView? = null
    var saveNewSalesmanButton: TextView? = null
    var cancelAddSalesmanButton: TextView? = null
    var noSalesmenTextView: TextView? = null
    var salesmenListLoadingImageView: ImageView? = null
    var salesmenList: LinearLayout? = null

    var username: TextInput? = null
    var name: TextInput? = null
    var password: TextInput? = null
    var phone: TextInput? = null

    val salesmenListView = SalesmenListView(this)
    private val addSalesmanView = AddSalesmanView(this)

    override fun onViewCreated(view: View) {
        getAndShowSalesmen()
    }

    fun onAddSalesmanButtonClicked() {
        mainViewContent?.content = addSalesmanView
    }

    fun onSaveNewSalesmanButtonClicked() {
        getAndShowSalesmen()
    }

    fun onCancelAddSalesmanButton() {
        getAndShowSalesmen()

    }

    fun onEditSalesman(salesman: SalesmanDs) {
        mainViewContent?.content = addSalesmanView
        username?.text = salesman.username
        phone?.text = salesman.phone
        name?.text = salesman.name

    }

    private fun getAndShowSalesmen() {
        mainViewContent?.content = salesmenListView
        salesmenList?.isVisible = false
        salesmenListLoadingImageView?.isVisible = true

        ServerCaller.getAllSalesmen(
                onSuccess = { xmlHttpRequest ->

                    if (xmlHttpRequest.status == 200.toShort()) {
                        salesmenList?.isVisible = true
                        salesmenListLoadingImageView?.isVisible = false

                        console.log(JSON.parse(xmlHttpRequest.responseText))
                        val salesmenResponse = JSON.parse<Json>(xmlHttpRequest.responseText).get("data") as? Json
                        val salesmen = salesmenResponse?.get("salesmen") as? Array<Json>

                        console.log(salesmen)
                        salesmen?.map {
                            val zonesIds = arrayListOf<Int>()
                            SalesmanDs(it["name"] as? String ?: "",
                                    it["username"] as? String ?: "",
                                    "",
                                    (it["zones"] as? Array<Int>)?.mapTo(zonesIds) { it } ?: arrayListOf(),
                                    it["phone"] as? String ?: "",
                                    it["id"] as? Int)
                        }?.forEach {
                            salesmenList?.addSalesman(it, this)
                        }
                    } else {

                        salesmenList?.isVisible = false
                        salesmenListLoadingImageView?.isVisible = false
                        noSalesmenTextView?.isVisible = true
                        noSalesmenTextView?.text = "Unknown error. Refresh page."
                    }
                },
                onError = {
                    salesmenList?.isVisible = false
                    salesmenListLoadingImageView?.isVisible = false
                    noSalesmenTextView?.isVisible = true
                    noSalesmenTextView?.text = "No internet connection. Refresh page."
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

        val zones: ArrayList<Int>,

        val approval: Boolean
)