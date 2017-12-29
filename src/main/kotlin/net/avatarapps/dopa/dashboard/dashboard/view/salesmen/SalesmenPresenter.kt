package net.avatarapps.dopa.dashboard.dashboard.view.salesmen

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
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
    var addSalesmanControlView: LinearLayout? = null
    var addSalesmenLoadingImageView: ImageView? = null
    var addSalesmanStatusText: TextView? = null
    private var salesmanId: Int? = null
    private var isEditSalesman: Boolean = false

    val salesmenListView = SalesmenListView(this)
    private val addSalesmanView = AddSalesmanView(this)

    override fun onViewCreated(view: View) {
        getAndShowSalesmen()
    }

    fun onAddSalesmanButtonClicked() {
        salesmanId = null
        mainViewContent?.content = addSalesmanView
        saveNewSalesmanButton?.text = "Save new salesman"
        password?.placeholder = "Password"
    }

    fun onSaveNewSalesmanButtonClicked() {

        if (validateField(name, "Name")) return

        if (validateField(username, "Username")) return

        if (isEditSalesman.not()) {
            if (validateField(password, "Password")) return
        }

        if (validateField(phone, "Phone")) return


        ServerCaller.updateSalesman(
                ServerCaller.UpdateSalesmanRequestDto(
                        ServerCaller.UpdateSalesmanDto(
                                salesmanId,
                                name?.text,
                                password?.text?.takeIf { it.isNotEmpty() },
                                username?.text,
                                phone?.text,
                                false,
                                arrayListOf())),
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        getAndShowSalesmen()

                    } else {
                        addSalesmanControlView?.isVisible = true
                        addSalesmenLoadingImageView?.isVisible = false

                    }
                },
                onError = {
                    addSalesmanControlView?.isVisible = true
                    addSalesmenLoadingImageView?.isVisible = false

                }
        )

    }

    private fun validateField(field: TextInput?, fieldName: String): Boolean {
        if (field?.text?.isNotEmpty() != true) {
            addSalesmanStatusText?.isVisible = true
            addSalesmanStatusText?.text = "$fieldName cannot be empty"
            return true
        } else {
            addSalesmanStatusText?.isVisible = false

        }
        return false
    }

    fun onCancelAddSalesmanButton() {
        getAndShowSalesmen()

    }


    fun onEditSalesman(salesman: SalesmanDs) {
        mainViewContent?.content = addSalesmanView
        username?.text = salesman.username
        phone?.text = salesman.phone
        name?.text = salesman.name
        salesmanId = salesman.id
        isEditSalesman = true
        saveNewSalesmanButton?.text = "Update salesman"
        password?.placeholder = "New password (optional)"


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