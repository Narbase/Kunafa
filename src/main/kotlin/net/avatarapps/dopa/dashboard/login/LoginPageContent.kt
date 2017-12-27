package net.avatarapps.dopa.dashboard.login

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.layout.JustifyContent
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 12/12/17.
 */
class LoginPageContent(
        val loginPresenter: LoginPresenter
) : ViewContent() {

    override fun DetachedView.contentDefinition() {
        horizontalLayout {
            id = "horizontalLayout"
            presenter = loginPresenter
            background = DopaColors.backgroundLight
            margin = 0.px
            padding = 0.px
            paddingBottom = 100.px
            width = matchParent
            height = matchParent
            alignItems = Alignment.Center
            justifyContent = JustifyContent.Center

            verticalLayout {
                background = Color.rgb(255, 255, 255)
                width = 480.px
                padding = 24.px
                height = wrapContent
                alignItems = Alignment.Center
                justifyContent = JustifyContent.Center

                imageView {
                    width = 188.px
                    height = 148.px
                    img.src = "/public/img/logo.png"
//                    img.src = "https://www.clker.com/cliparts/E/G/n/Y/R/v/blue-pharmacy-logo.svg"
                }

                textView {
                    width = matchParent
                    height = wrapContent
                    text = "Dopa Dashboard"
                    textColor = Color.rgb(55, 55, 55)
                    textSize = 32.px
                    textAlign = TextView.TextAlign.Center
                }

                loginPresenter.usernameTextInput = textInput {
                    width = matchParent
                    height = wrapContent
                    placeholder = "Username"
                    textSize = 16.px
                    margin = 4.px
                    padding = 4.px
                }

                loginPresenter.passwordTextInput = textInput {
                    width = matchParent
                    height = wrapContent
                    placeholder = "password"
                    textSize = 16.px
                    margin = 4.px
                    padding = 4.px
                }

                loginPresenter.loginButton = button {
                    width = matchParent
                    height = 38.px
                    button.textContent = "Login"
                    marginTop = 18.px
                }

                loginPresenter.loadingImageView = imageView {
                    marginTop = 18.px
                    width = 40.px
                    height = 40.px
                    alignSelf = Alignment.Center
                    isVisible = false
                    img.src = "/public/img/loading.gif"
                }
            }
        }
    }
}

interface LoginPageNavigator {
    fun onLoggedInSuccessful()
}
