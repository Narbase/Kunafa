package net.avatarapps.dopa.dashboard

import net.avatarapps.dopa.dashboard.dashboard.DashboardPageContent
import net.avatarapps.dopa.dashboard.dashboard.DashboardPresenter
import net.avatarapps.dopa.dashboard.login.LoginPageContent
import net.avatarapps.dopa.dashboard.login.LoginPresenter
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.*
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent

fun main(args: Array<String>) {
    App().setup()
}

class App {

    fun setup() {
        val appPresenter = AppPresenter()

        val loginPresenter = LoginPresenter()
        val loginPage = LoginPageContent(loginPresenter, appPresenter)

        val dashboardPage = DashboardPageContent(appPresenter)
        val dashboardPresenter = DashboardPresenter()

        appPresenter.loginPage = loginPage
        appPresenter.dashboardPage = dashboardPage

        page {
            viewContainer {
                presenter = appPresenter
                id = "mainView"
                width = matchParent
                height = matchParent
                content = loginPage
            }
        }
    }
}

