package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.TextInput
import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View
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

    private val searchInteractor = DrugsBounceSearchInteractor(this)

    override fun onViewCreated(view: View) {
        selectedDrug = null
        saveNewSmartOfferButton?.text = "Save new smart offer"
        hideDrugSearchUi()

        drugNameText?.onClick = { showDrugSearchUi() }
        drugNameTextInput?.element?.oninput = { onDrugSearchTermChanged() }
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

    fun onSaveNewSmartOfferButtonClicked() {

        val drugId = selectedDrug?.id
        if (drugId == null) {
            addSmartOfferStatusText?.isVisible = true
            addSmartOfferStatusText?.text = "No drug is selected"
            return
        } else {
            addSmartOfferStatusText?.isVisible = false
        }
        if (validateField(descriptionTextInput, "Description")) return

        ServerCaller.addSmartOffer(AddSmartOfferRequestDto(drugId, descriptionTextInput?.text?: ""),
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        onCancelAddSmartOfferButton()
                    }
                },
                onError = {}
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
        val offerDescription: String

)