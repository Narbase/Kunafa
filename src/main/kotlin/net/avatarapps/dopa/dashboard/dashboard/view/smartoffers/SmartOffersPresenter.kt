package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
import net.avatarapps.dopa.dashboard.dashboard.view.zones.ZoneDs
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.LinearLayout

class SmartOffersPresenter(val smartOffersView: SmartOffersView) : DashboardPlainPresenter() {

    var addSmartOfferButton: TextView? = null
    var noSmartOffersTextView: TextView? = null
    var smartOffersListLoadingImageView: ImageView? = null
    var smartOffersList: LinearLayout? = null


    var noZonesTextView: TextView? = null
    var zonesListLoadingImageView: ImageView? = null
    var zonesList: LinearLayout? = null

    private var smartOfferId: Int? = null


    override fun onViewCreated(view: View) {
        getAndShowSmartOffers()
    }

    fun onAddSmartOfferButtonClicked() {
        smartOfferId = null
        smartOffersView.setViewToAddOffer()
    }

    fun onCancelAddSmartOfferButton() {
        getAndShowSmartOffers()
    }

    fun onRemoveSmartOffer(smartOffer: SmartOfferDs, removeButton: TextView) {
        val oldText = removeButton.text
        val oldOnClick = removeButton.onClick
        removeButton.text = "Loading.."
        ServerCaller.removeSmartOffer(RemoveSmartOffersRequestDto(smartOffer.id),
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        getAndShowSmartOffers()
                    }
                },
                onError = {

                }
        )

    }

    private fun getAndShowSmartOffers() {
        smartOffersView.setViewToOffersList()
        showLoadingImage()
        ServerCaller.getAllSmartOffers(
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        hideLoadingImage()
                        val jsonResponse = JSON.parse<GetSmartOffersDataResponseDto>(xmlHttpRequest.responseText)
                        jsonResponse.data.smartOffersList.forEach {
                            smartOffersList?.addSmartOffer(
                                    SmartOfferDs(
                                            it.id,
                                            it.offerDescription,
                                            it.drugId,
                                            it.drugName,
                                            it.targetIsAll,
                                            it.targetContainsZones,
                                            it.targetContainsLabels,
                                            it.targetContainsPharmaciesWithPreviousInteractions,
                                            it.targetedLabels,
                                            it.targetedZonesNames)
                                    ,
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
        val drugName: String,
        val targetIsAll: Boolean,
        val targetContainsZones: Boolean,
        val targetContainsLabels: Boolean,
        val targetContainsPharmaciesWithPreviousInteractions: Boolean,
        val targetedLabels: String?,
        var targetedZonesNames: Array<String>
)

data class RemoveSmartOffersRequestDto(
        val id: Int
)