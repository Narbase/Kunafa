package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.TextInput
import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import net.avatarapps.kunafa.core.presenter.Presenter

class AddSmartOfferPresenter(
        private val smartOffersPresenter: SmartOffersPresenter) : Presenter(){
    var view: AddSmartOfferView? = null
    var saveNewSmartOfferButton: TextView? = null
    var cancelAddSmartOfferButton: TextView? = null

    var username: TextInput? = null
    var drugNameTextInput: TextInput? = null
    var password: TextInput? = null
    var phone: TextInput? = null
    var addSmartOfferControlView: LinearLayout? = null
    var addSmartOffersLoadingImageView: ImageView? = null
    var addSmartOfferStatusText: TextView? = null

    var suggestionListLayout: LinearLayout? = null
    var drugNameText: TextView? = null

    private val searchInteractor = DrugsBounceSearchInteractor(this)

    override fun onViewCreated(view: View) {
        saveNewSmartOfferButton?.text = "Save new smart offer"
        password?.placeholder = "Password"
        hideDrugSearchUi()

        drugNameText?.onClick = { showDrugSearchUi() }
        drugNameTextInput?.element?.oninput = { onDrugSearchTermChanged() }
    }

    private fun onDrugSearchTermChanged() {
        val searchTerm = drugNameTextInput?.text ?: ""
        searchInteractor.searchFor(searchTerm)
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

        if (validateField(drugNameTextInput, "Name")) return
        if (validateField(username, "Username")) return
        if (validateField(phone, "Phone")) return


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
        smartOffersPresenter.onCancelAddSmartOfferButton()

    }

    fun onDrugSelected(drugId: Int, drugName: String) {
        hideDrugSearchUi()
        drugNameText?.text = drugName
    }

    fun onSearchResultReady(drugs: Map<Int, String>) {
        view?.onSearchResultReady(drugs)
    }


}