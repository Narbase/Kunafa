package net.avatarapps.dopa.dashboard.network

import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.window

object ServerCaller {

    private const val BASE_URL = "http://localhost:4567"

    fun doLoginRequest(username: String?, password: String?, onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {

        post(
                url = "/oauth/token/agent",
                headers = mapOf("Authorization" to "Basic " + window.btoa("$username:$password")),
                onSuccess =  onSuccess,
                onError = onError)

    }

    private fun post(
            url: String,
            headers: Map<String, String>? = null,
            onSuccess: (XMLHttpRequest) -> Unit,
            onError: () -> Unit){

        val xmlHttp = XMLHttpRequest()
        xmlHttp.open("POST", "$BASE_URL$url", true)
        xmlHttp.setRequestHeader("Content-Type", "application/json")
        headers?.forEach {
            xmlHttp.setRequestHeader(it.key, it.value)
        }

        xmlHttp.onerror = {
            onError()
        }

        xmlHttp.onload = {
            onSuccess(xmlHttp)

        }
        xmlHttp.send(null)
    }
}