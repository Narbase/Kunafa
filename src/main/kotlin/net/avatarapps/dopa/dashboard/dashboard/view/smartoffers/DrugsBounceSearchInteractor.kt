package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

import net.avatarapps.dopa.dashboard.network.ServerCaller
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.window

class DrugsBounceSearchInteractor(val presenter: AddSmartOfferPresenter) {


    private var request: Int? = null
    private var serverRequest: XMLHttpRequest? = null

    fun searchFor(term: String) {
        request?.let { window.clearTimeout(it) }
        serverRequest?.abort()
        this.request = window.setTimeout({
            searchDrugs(term)
        }, 200)
    }


    private fun searchDrugs(term: String) {
        this.serverRequest =  ServerCaller.searchDrugs(SearchDrugsRequestDto(term),
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        val dataResponse = JSON.parse<SearchDrugsDataResponseDto>(xmlHttpRequest.responseText)
                        val map = mutableMapOf<Int, String>()
                        dataResponse.data.drugs.forEach {
                            map[it.id] = it.name
                        }
                        onSearchResultReady(map)
                    }
                },
                onError = {

                }
        )
    }

    private fun onSearchResultReady(drugs: Map<Int, String>) {
        presenter.onSearchResultReady(drugs)
    }
}

data class SearchDrugsRequestDto(
        val term: String
)


data class SearchDrugsDataResponseDto(
        val data: SearchDrugsResponseDto
)

data class SearchDrugsResponseDto(
        val drugs: Array<SearchDrugDto>
)

data class SearchDrugDto (
        val id: Int,
        val name: String
)