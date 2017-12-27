package net.avatarapps.dopa.dashboard.network

import net.avatarapps.dopa.dashboard.storage.StorageManager
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.window

object ServerCaller {

    private const val BASE_URL = "http://localhost:4567"
    private val accessToken = StorageManager.accessToken

    fun doLoginRequest(username: String?, password: String?, onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        post(
                url = "/oauth/token/agent",
                headers = mapOf("Authorization" to "Basic " + window.btoa("$username:$password")),
                onSuccess = onSuccess,
                onError = onError)

    }

    fun getAllSalesmen(onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        get(
                url = "/api/agent/v1/salesmen/all",
                headers = mapOf("Authorization" to (accessToken?:"")),
                onSuccess = onSuccess,
                onError = onError
        )

    }

    private fun post(
            url: String,
            headers: Map<String, String>? = null,
            onSuccess: (XMLHttpRequest) -> Unit,
            onError: () -> Unit) {

        makeRequest(
                HTTP_POST_VERB,
                url,
                headers,
                onSuccess,
                onError
        )
    }

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
            onError: () -> Unit) {

        val xmlHttp = XMLHttpRequest()
        xmlHttp.open(requestVerb, "$BASE_URL$url", true)
        xmlHttp.setRequestHeader("Content-Type", "application/json")

        headers?.forEach { xmlHttp.setRequestHeader(it.key, it.value) }
        xmlHttp.onerror = { onError() }
        xmlHttp.onload = { onSuccess(xmlHttp) }
        xmlHttp.send(null)
    }

    const val HTTP_GET_VERB = "GET"
    const val HTTP_POST_VERB = "POST"

}