package net.avatarapps.dopa.dashboard.login

import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.presenter.Presenter

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
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

    override fun onViewCreated(view: View) {
        loginButton?.onClick = {
            println("I am in presenter clicked")
//            navigator.onLoggedInSuccessful()
            loadingImageView?.isVisible = true
            loginButton?.isVisible = false
        }

        loadingImageView?.onClick = {
            println("I am in presenter clicked")
//            navigator.onLoggedInSuccessful()
            loadingImageView?.isVisible = false
            loginButton?.isVisible = true
        }
    }



}