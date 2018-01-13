package net.avatarapps.dopa.dashboard

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPageContent
import net.avatarapps.dopa.dashboard.dashboard.DashboardPresenter
import net.avatarapps.dopa.dashboard.login.LoginPageContent
import net.avatarapps.dopa.dashboard.login.LoginPresenter
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.Page.title
import net.avatarapps.kunafa.core.components.layout.*
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color
import net.avatarapps.kunafa.core.presenter.Presenter
import kotlin.browser.window

fun main(args: Array<String>) {
    App().setup()
}

class App {

    val version = "0.9.4"
    fun setup() {
        val appPresenter = AppPresenter()

        val loginPresenter = LoginPresenter(appPresenter)
        val loginPage = LoginPageContent(loginPresenter)

        val dashboardPage = DashboardPageContent(appPresenter)
        val dashboardPresenter = DashboardPresenter()

        appPresenter.loginPage = loginPage
        appPresenter.dashboardPage = dashboardPage

        page {
            title = "Dopa dashboard"
            viewContainer {
                presenter = appPresenter
                id = "mainView"
                width = matchParent
                height = matchParent
                element.style.fontFamily = "sans-serif"
            }
        }
    }
}

