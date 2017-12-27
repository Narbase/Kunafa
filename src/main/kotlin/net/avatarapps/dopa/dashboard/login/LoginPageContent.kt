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
                height = wrapContent
                alignItems = Alignment.Center
                justifyContent = JustifyContent.Center

                verticalLayout {
                    width = matchParent
                    height = wrapContent
                    background = DopaColors.mainDark
                    alignItems = Alignment.Center
                    justifyContent = JustifyContent.Center
                    padding = 18.px

                    imageView {
                        width = 188.px
                        height = wrapContent
                        img.src = "/public/img/logo.png"
//                    img.src = "https://www.clker.com/cliparts/E/G/n/Y/R/v/blue-pharmacy-logo.svg"
                    }
                }

                verticalLayout {
                    padding = 24.px


                    textView {
                        width = matchParent
                        height = wrapContent
                        text = "Dopa Dashboard"
                        textColor = DopaColors.mainLight
                        textSize = 32.px
                        textAlign = TextView.TextAlign.Center
                        marginBottom = 16.px
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
                        type = "password"
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

                    loginPresenter.statusTextView = textView {
                        marginTop = 8.px
                        width = matchParent
                        height = wrapContent
                        textSize = 14.px
                        textAlign = TextView.TextAlign.Left
                        isVisible = true
                    }
                }
            }
        }
    }
}

interface LoginPageNavigator {
    fun onLoggedInSuccessful()
}
