package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
import net.avatarapps.dopa.dashboard.dashboard.view.salesmenrequests.ResponseDto
import net.avatarapps.dopa.dashboard.dashboard.view.zones.ZoneDs
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.LinearLayout

class SmartOffersPresenter : DashboardPlainPresenter() {

    var addSmartOfferButton: TextView? = null
    var saveNewSmartOfferButton: TextView? = null
    var cancelAddSmartOfferButton: TextView? = null
    var noSmartOffersTextView: TextView? = null
    var smartOffersListLoadingImageView: ImageView? = null
    var smartOffersList: LinearLayout? = null

    var username: TextInput? = null
    var name: TextInput? = null
    var password: TextInput? = null
    var phone: TextInput? = null
    var addSmartOfferControlView: LinearLayout? = null
    var addSmartOffersLoadingImageView: ImageView? = null
    var addSmartOfferStatusText: TextView? = null

    var noZonesTextView: TextView? = null
    var zonesListLoadingImageView: ImageView? = null
    var zonesList: LinearLayout? = null

    private var smartOfferId: Int? = null
    private var isEditSmartOffer: Boolean = false

    val smartOffersListView = SmartOffersListView(this)
    private val addSmartOfferView = AddSmartOfferView(this)

    override fun onViewCreated(view: View) {
        getAndShowSmartOffers()
    }

    fun onAddSmartOfferButtonClicked() {
        smartOfferId = null
        mainViewContent?.content = addSmartOfferView
        isEditSmartOffer = false
        saveNewSmartOfferButton?.text = "Save new smartOffer"
        password?.placeholder = "Password"
        showZones()
    }

    private val zonesMap: MutableMap<ZoneDs, Checkbox?> = mutableMapOf()

    private fun showZones(smartOffer: SmartOfferDs? = null) {
        showLoadingImage()
        zonesMap.clear()
    }

    fun onSaveNewSmartOfferButtonClicked() {

        if (validateField(name, "Name")) return

        if (validateField(username, "Username")) return

        if (isEditSmartOffer.not()) {
            if (validateField(password, "Password")) return
        }

        if (validateField(phone, "Phone")) return

        val zonesIds = arrayListOf<Int>()
        zonesMap.filter {
            it.value?.isChecked == true
        }.mapTo(zonesIds) {
            it.key.id
        }

        // Update smart offer
    }

    private fun validateField(field: TextInput?, fieldName: String): Boolean {
        if (field?.text?.isNotEmpty() != true) {
            addSmartOfferStatusText?.isVisible = true
            addSmartOfferStatusText?.text = "$fieldName cannot be empty"
            return true
        } else {
            addSmartOfferStatusText?.isVisible = false

        }
        return false
    }

    fun onCancelAddSmartOfferButton() {
        getAndShowSmartOffers()

    }


    fun onEditSmartOffer(smartOffer: SmartOfferDs) {
        mainViewContent?.content = addSmartOfferView
//        username?.text = smartOffer.username
//        phone?.text = smartOffer.phone
//        name?.text = smartOffer.name
        smartOfferId = smartOffer.id
        isEditSmartOffer = true
        saveNewSmartOfferButton?.text = "Update smartOffer"
        password?.placeholder = "New password (optional)"
        showZones(smartOffer)


    }

    private fun getAndShowSmartOffers() {
        mainViewContent?.content = smartOffersListView
        showLoadingImage()

        ServerCaller.getAllSmartOffers(
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        hideLoadingImage()
                        val jsonResponse = JSON.parse<GetSmartOffersDataResponseDto>(xmlHttpRequest.responseText)
                        jsonResponse.data.smartOffersList.forEach {
                            smartOffersList?.addSmartOffer(
                                    SmartOfferDs(it.id, it.offerDescription, it.drugId, it.drugName),
                                    this)
                        }
                    }
                },
                onError = {

                }
        )

        // Get offers from the server
    }

    private fun showLoadingImage() {
        smartOffersList?.isVisible = false
        smartOffersListLoadingImageView?.isVisible = true
    }


    private fun hideLoadingImage() {
        smartOffersList?.isVisible = true
        smartOffersListLoadingImageView?.isVisible = false
    }

}


data class GetSmartOffersDataResponseDto(
        val data: GetSmartOffersResponseDto
)

data class GetSmartOffersResponseDto(
        val smartOffersList: Array<SmartOfferInListDto>
)


data class SmartOfferInListDto(
        val id: Int,
        val offerDescription: String,
        val drugId: Int,
        val drugName: String
)