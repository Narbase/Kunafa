package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

import kotlin.browser.window

class DrugsBounceSearchInteractor(val presenter: AddSmartOfferPresenter) {

    val dummyData = mapOf(
            0 to "Panadol",
            1 to "Aspirin",
            2 to "Anazoal",
            3 to "Mafipain",
            4 to "Something dangerous"
    )

    private var request: Int? = null

    fun searchFor(term: String) {
        request?.let { window.clearTimeout(it) }
        this.request = window.setTimeout({
            onSearchResultReady(dummyData.filter { it.value.toLowerCase().contains(term.toLowerCase()) })
        }, 200)
    }

    private fun onSearchResultReady(drugs: Map<Int, String>) {
        presenter.onSearchResultReady(drugs)
    }
}
