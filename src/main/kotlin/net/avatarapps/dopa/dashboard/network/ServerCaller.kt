package net.avatarapps.dopa.dashboard.network

import net.avatarapps.dopa.dashboard.dashboard.view.smartoffers.RemoveSmartOffersRequestDto
import net.avatarapps.dopa.dashboard.dashboard.view.smartoffers.SearchDrugsRequestDto
import net.avatarapps.dopa.dashboard.storage.StorageManager
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.window

object ServerCaller {

    const val BASE_URL = "http://localhost:4567"
    private val accessToken
        get() = StorageManager.accessToken

    fun doLoginRequest(username: String?, password: String?, onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        post(
                url = "/oauth/token/agent",
                headers = mapOf("Authorization" to "Basic " + window.btoa("$username:$password")),
                onSuccess = onSuccess,
                onError = onError)

    }

    fun getAllZones(onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        get(
                url = "/api/agent/v1/zones/all",
                headers = mapOf("Authorization" to (accessToken ?: "")),
                onSuccess = onSuccess,
                onError = onError
        )

    }

    fun updateZone(dto: UpdateZoneRequestDto, onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        val body = JSON.stringify(dto)
        post(
                url = "/api/agent/v1/zones/update",
                headers = mapOf("Authorization" to (accessToken ?: ""),
                        "Content-Type" to "application/json"),
                onSuccess = onSuccess,
                onError = onError,
                body = body
        )
    }


    fun getAllSalesmen(onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        get(
                url = "/api/agent/v1/salesmen/all",
                headers = mapOf("Authorization" to (accessToken ?: "")),
                onSuccess = onSuccess,
                onError = onError
        )

    }

    fun updateSalesman(dto: UpdateSalesmanRequestDto, onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        val body = JSON.stringify(dto)
        post(
                url = "/api/agent/v1/salesmen/update",
                headers = mapOf(
                        "Authorization" to (accessToken ?: ""),
                        "Content-Type" to "application/json"),
                onSuccess = onSuccess,
                onError = onError,
                body = body
        )
    }

    fun getAllSmartOffers(onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        get(
                url = "/api/agent/v1/smart_offer/all",
                headers = mapOf("Authorization" to (accessToken ?: "")),
                onSuccess = onSuccess,
                onError = onError
        )

    }

    fun removeSmartOffer(dto: RemoveSmartOffersRequestDto, onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit ){
         val body = JSON.stringify(dto)
        post(
                url = "/api/agent/v1/smart_offer/remove",
                headers = mapOf(
                        "Authorization" to (accessToken ?: ""),
                        "Content-Type" to "application/json"),
                onSuccess = onSuccess,
                onError = onError,
                body = body
        )
    }

    fun getAllSalesmenRequests(onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        get(
                url = "/api/agent/v1/salesmen_request/all",
                headers = mapOf("Authorization" to (accessToken ?: "")),
                onSuccess = onSuccess,
                onError = onError
        )

    }

    fun updateSalesmanRequest(dto: UpdateRequestRequestDto, onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        val body = JSON.stringify(dto)
        post(
                url = "/api/agent/v1/salesmen_request/update",
                headers = mapOf(
                        "Authorization" to (accessToken ?: ""),
                        "Content-Type" to "application/json"),
                onSuccess = onSuccess,
                onError = onError,
                body = body
        )
    }

    fun searchDrugs(dto: SearchDrugsRequestDto, onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) : XMLHttpRequest{
        val body = JSON.stringify(dto)
        return post(
                url = "/api/agent/v1/drugs/search",
                headers = mapOf(
                        "Authorization" to (accessToken ?: ""),
                        "Content-Type" to "application/json"),
                onSuccess = onSuccess,
                onError = onError,
                body = body
        )
    }

    data class UpdateRequestRequestDto(
            val id: Int?,
            val isApproved: Boolean?
    )

    data class UpdateSalesmanRequestDto(
            val salesmanDto: UpdateSalesmanDto?
    )

    data class UpdateSalesmanDto(
            val id: Int?,
            val name: String?,
            val password: String?,
            val username: String?,
            val phone: String?,
            val approval: Boolean?,
            val zones: ArrayList<Int>
    )


    data class UpdateZoneRequestDto(
            val zone: UpdateZoneDto?
    )


    data class UpdateZoneDto(
            val id: Int?,
            val name: String,
            val neighbourhoods: List<Int>
    )


    private fun post(
            url: String,
            headers: Map<String, String>? = null,
            onSuccess: (XMLHttpRequest) -> Unit,
            onError: () -> Unit,
            body: String? = null) = makeRequest(
                    HTTP_POST_VERB,
                    url,
                    headers,
                    onSuccess,
                    onError,
                    body
            )

    private fun get(
            url: String,
            headers: Map<String, String>? = null,
            onSuccess: (XMLHttpRequest) -> Unit,
            onError: () -> Unit) {

        makeRequest(
                HTTP_GET_VERB,
                url,
                headers,
                onSuccess,
                onError
        )
    }

    private fun makeRequest(
            requestVerb: String,
            url: String,
            headers: Map<String, String>? = null,
            onSuccess: (XMLHttpRequest) -> Unit,
            onError: () -> Unit,
            body: String? = null): XMLHttpRequest {

        val xmlHttp = XMLHttpRequest()
        xmlHttp.open(requestVerb, "$BASE_URL$url", true)
        xmlHttp.setRequestHeader("Content-Type", "application/json")

        headers?.forEach { xmlHttp.setRequestHeader(it.key, it.value) }
        xmlHttp.onerror = { onError() }
        xmlHttp.onload = { onSuccess(xmlHttp) }
        xmlHttp.send(body)
        return xmlHttp
    }

    const val HTTP_GET_VERB = "GET"
    const val HTTP_POST_VERB = "POST"

}