package net.avatarapps.dopa.dashboard.dashboard.view.salesmen

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
import net.avatarapps.dopa.dashboard.dashboard.view.zones.ZoneDs
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.*
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
    var requiresAgentApprovalCheckbox: Checkbox? = null

    var noZonesTextView: TextView? = null
    var zonesListLoadingImageView: ImageView? = null
    var zonesList: LinearLayout? = null

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
        isEditSalesman = false
        saveNewSalesmanButton?.text = "Save new salesman"
        password?.placeholder = "Password"
        showZones()
    }

    private val zonesMap: MutableMap<ZoneDs, Checkbox?> = mutableMapOf()

    private fun showZones(salesman: SalesmanDs? = null) {
        zonesList?.isVisible = false
        zonesListLoadingImageView?.isVisible = true
        zonesMap.clear()

        ServerCaller.getAllZones(
                onSuccess = { xmlHttpRequest ->

                    if (xmlHttpRequest.status == 200.toShort()) {
                        zonesList?.isVisible = true
                        zonesListLoadingImageView?.isVisible = false

                        console.log(JSON.parse(xmlHttpRequest.responseText))
                        val zonesResponse = JSON.parse<Json>(xmlHttpRequest.responseText).get("data") as? Json
                        val zones = zonesResponse?.get("zones") as? Array<Json>

                        console.log(zones)
                        zones?.map {
                            val zonesIds = arrayListOf<Int>()
                            ZoneDs(it["id"] as? Int ?: 0,
                                    it["name"] as? String ?: "",
                                    (it["neighbourhoods"] as? Array<Int>)?.mapTo(zonesIds) { it } ?: arrayListOf()
                            )
                        }?.forEach {
                            val checkbox = zonesList?.addZone(it, this, isSelected =  (it.id in salesman?.zoneId?: arrayListOf()))
                            zonesMap[it] = checkbox
                        }
                    } else {

                        zonesList?.isVisible = false
                        zonesListLoadingImageView?.isVisible = false
                        noZonesTextView?.isVisible = true
                        noZonesTextView?.text = "Unknown error. Refresh page."
                    }
                },
                onError = {
                    zonesList?.isVisible = false
                    zonesListLoadingImageView?.isVisible = false
                    noZonesTextView?.isVisible = true
                    noZonesTextView?.text = "No internet connection. Refresh page."
                }
        )
    }

    fun onSaveNewSalesmanButtonClicked() {

        if (validateField(name, "Name")) return

        if (validateField(username, "Username")) return

        if (isEditSalesman.not()) {
            if (validateField(password, "Password")) return
        }

        if (validateField(phone, "Phone")) return

        val zonesIds = arrayListOf<Int>()
        zonesMap.filter {
            it.value?.isChecked == true
        }.mapTo(zonesIds) {
            it.key.id
        }

        ServerCaller.updateSalesman(
                ServerCaller.UpdateSalesmanRequestDto(
                        ServerCaller.UpdateSalesmanDto(
                                salesmanId,
                                name?.text,
                                password?.text?.takeIf { it.isNotEmpty() },
                                username?.text,
                                phone?.text,
                                requiresAgentApprovalCheckbox?.isChecked,
                                zonesIds)),
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
        requiresAgentApprovalCheckbox?.isChecked = salesman.agentApproval
        isEditSalesman = true
        saveNewSalesmanButton?.text = "Update salesman"
        password?.placeholder = "New password (optional)"
        showZones(salesman)


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

                        val salesmenResponse = JSON.parse<Json>(xmlHttpRequest.responseText).get("data") as? Json
                        val salesmen = salesmenResponse?.get("salesmen") as? Array<Json>

                        salesmen?.map {
                            val zonesIds = arrayListOf<Int>()
                            SalesmanDs(it["name"] as? String ?: "",
                                    it["username"] as? String ?: "",
                                    "",
                                    (it["zones"] as? Array<Int>)?.mapTo(zonesIds) { it } ?: arrayListOf(),
                                    it["phone"] as? String ?: "",
                                    it["approval"] as? Boolean ?: false,
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

data class GetSalesmenDataResponseDto(
        val data: GetSalesmenResponseDto
)

class GetSalesmenResponseDto(
        val salesmen: Array<SalesmanInListDto>
)

class SalesmanInListDto(
        val id: Int?,

        val name: String,

        val username: String,

        val phone: String,

        val zones: Array<Int>,

        val approval: Boolean
)