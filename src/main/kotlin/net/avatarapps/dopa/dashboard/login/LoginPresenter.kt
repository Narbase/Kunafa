package net.avatarapps.dopa.dashboard.login

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.dopa.dashboard.storage.StorageManager
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.drawable.Color
import net.avatarapps.kunafa.core.presenter.Presenter
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.window
import kotlin.js.Json

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 12/12/17.
 */
class LoginPresenter(
        private val navigator: LoginPageNavigator
) : Presenter() {
    var loginButton: ButtonView? = null
    var usernameTextInput: TextInput? = null
    var passwordTextInput: TextInput? = null
    var loadingImageView: ImageView? = null
    var statusTextView: TextView? = null

    override fun onViewCreated(view: View) {
        loginButton?.onClick = {
            loadingImageView?.isVisible = true
            loginButton?.isVisible = false
            statusTextView?.isVisible = false

            ServerCaller.doLoginRequest(
                    usernameTextInput?.text,
                    passwordTextInput?.text,
                    onSuccess = { xmlHttp ->
                        if (xmlHttp.status == 200.toShort()) {
                            loadingImageView?.isVisible = false
                            loginButton?.isVisible = true
                            statusTextView?.textColor = DopaColors.greenLight
                            statusTextView?.text = "Logged in successful"
                            statusTextView?.isVisible = true
                            val accessToken = JSON.parse<Json>(xmlHttp.responseText).get("access_token") as? String
                            StorageManager.accessToken = accessToken
                            navigator.onLoggedInSuccessful()
                        } else {
                            loadingImageView?.isVisible = false
                            loginButton?.isVisible = true
                            statusTextView?.textColor = DopaColors.redLight
                            statusTextView?.text = "Username or password are incorrect"
                            statusTextView?.isVisible = true
                        }
                    },
                    onError = {
                        loadingImageView?.isVisible = false
                        loginButton?.isVisible = true
                        statusTextView?.textColor = DopaColors.separatorLight
                        statusTextView?.text = "No internet connection"
                        statusTextView?.isVisible = true
                    }
            )

        }

    }

}