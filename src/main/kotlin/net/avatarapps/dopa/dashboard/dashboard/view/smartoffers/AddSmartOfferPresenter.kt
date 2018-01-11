package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

import net.avatarapps.dopa.dashboard.dashboard.view.zones.ZoneDs
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.presenter.Presenter

class AddSmartOfferPresenter(
        private val smartOffersPresenter: SmartOffersPresenter) : Presenter() {
    var view: AddSmartOfferView? = null
    var saveNewSmartOfferButton: TextView? = null
    var cancelAddSmartOfferButton: TextView? = null

    var drugNameTextInput: TextInput? = null
    var descriptionTextInput: TextInput? = null
    var addSmartOfferControlView: LinearLayout? = null
    var addSmartOffersLoadingImageView: ImageView? = null
    var addSmartOfferStatusText: TextView? = null

    var suggestionListLayout: LinearLayout? = null
    var drugNameText: TextView? = null
    var selectedDrug: SearchDrugDto? = null


    private val zonesMap: MutableMap<ZoneDs, Checkbox?> = mutableMapOf()

    private val searchInteractor = DrugsBounceSearchInteractor(this)

    override fun onViewCreated(view: View) {
        selectedDrug = null
        saveNewSmartOfferButton?.text = "Save new smart offer"
        hideDrugSearchUi()

        drugNameText?.onClick = { showDrugSearchUi() }
        drugNameTextInput?.element?.oninput = { onDrugSearchTermChanged() }
        showZones()
    }

    private fun onDrugSearchTermChanged() {
        val searchTerm = drugNameTextInput?.text ?: ""
        if (searchTerm.count() > 1) {
            searchInteractor.searchFor(searchTerm)
        }
    }

    private fun showDrugSearchUi() {
        drugNameTextInput?.isVisible = true
        suggestionListLayout?.isVisible = true
        drugNameTextInput?.element?.focus()
    }

    private fun hideDrugSearchUi() {
        drugNameTextInput?.isVisible = false
        suggestionListLayout?.isVisible = false
    }

    fun onSaveNewSmartOfferButtonClicked(
            targetIsAll: Boolean,
            targetContainsZones: Boolean,
            targetContainsLabels: Boolean,
            targetContainsPharmaciesWithPreviousInteractions: Boolean,
            targetedLabels: String) {

        val drugId = selectedDrug?.id
        if (drugId == null) {
            addSmartOfferStatusText?.isVisible = true
            addSmartOfferStatusText?.text = "No drug is selected"
            return
        } else {
            addSmartOfferStatusText?.isVisible = false
        }
        if (validateField(descriptionTextInput, "Description")) return

        val zonesIds: ArrayList<Int> = arrayListOf()
        if (targetContainsZones) {
            zonesMap.filter { it.value?.isChecked == true }
                    .mapTo(zonesIds) { it.key.id }
        }

        ServerCaller.addSmartOffer(
                AddSmartOfferRequestDto(
                        drugId,
                        descriptionTextInput?.text ?: "",
                        targetIsAll,
                        targetContainsZones,
                        targetContainsLabels,
                        targetContainsPharmaciesWithPreviousInteractions,
                        if (targetContainsLabels) targetedLabels else "",
                        zonesIds),
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        onCancelAddSmartOfferButton()
                    }
                },
                onError = {}
        )
    }

    private fun showZones() {
        view?.showZonesLoadingImage()
        zonesMap.clear()

        ServerCaller.getAllZones(
                onSuccess = { xmlHttpRequest ->

                    if (xmlHttpRequest.status == 200.toShort()) {
                        view?.hideZonesLoadingImage()

                        console.log(JSON.parse(xmlHttpRequest.responseText))
                        val zonesResponse = JSON.parse<GetZonesDataResponse>(xmlHttpRequest.responseText).data
                        val zones = zonesResponse.zones

                        console.log(zones)
                        zones.map {
                            val zonesIds = arrayListOf<Int>()
                            ZoneDs(it.id,
                                    it.name,
                                    it.neighbourhoods.mapTo(zonesIds) { it }
                            )
                        }.forEach {
                            val checkbox = view?.addZoneCheckbox(it.name)
                            zonesMap[it] = checkbox
                        }
                    } else {
                        view?.showNoZonesText()
                    }
                },
                onError = {
                    view?.showNoZonesText()
                }
        )
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
        smartOffersPresenter.onCancelAddSmartOfferButton()
    }


    fun onDrugSelected(drugId: Int, drugName: String) {
        hideDrugSearchUi()
        drugNameText?.text = drugName
        selectedDrug = SearchDrugDto(drugId, drugName)
    }

    fun onSearchResultReady(drugs: Map<Int, String>) {
        view?.onSearchResultReady(drugs)
    }


}

data class AddSmartOfferRequestDto(
        val drugId: Int,
        val offerDescription: String,
        val targetIsAll: Boolean,
        val targetContainsZones: Boolean,
        val targetContainsLabels: Boolean,
        val targetContainsPharmaciesWithPreviousInteractions: Boolean,
        val targetedLabels: String,
        var targetedZonesIds: ArrayList<Int> = arrayListOf()

)


data class GetZonesDataResponse(
        val data: GetZonesResponse
)

data class GetZonesResponse(
        val zones: Array<GetZoneDto>
)

data class GetZoneDto(
        val id: Int,
        val name: String,
        val neighbourhoods: Array<Int>
)