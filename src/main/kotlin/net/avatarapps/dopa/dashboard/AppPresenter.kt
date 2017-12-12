package net.avatarapps.dopa.dashboard

import net.avatarapps.dopa.dashboard.dashboard.DashboardNavigator
import net.avatarapps.dopa.dashboard.dashboard.DashboardPageContent
import net.avatarapps.dopa.dashboard.dashboard.DashboardPresenter
import net.avatarapps.dopa.dashboard.login.LoginPageContent
import net.avatarapps.dopa.dashboard.login.LoginPageNavigator
import net.avatarapps.kunafa.core.components.layout.ViewContainer
import net.avatarapps.kunafa.core.presenter.Presenter

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 12/12/17.
 */
class AppPresenter(
) : Presenter(), LoginPageNavigator, DashboardNavigator {

    var loginPage: LoginPageContent? = null
    var dashboardPage : DashboardPageContent? = null
    var mainView: ViewContainer? = null

    override fun onLoggedInSuccessful() {
        if (mainView == null) println("Main view is null")
        mainView?.content = dashboardPage
    }
    override fun onLogoutSelected() {
        mainView?.content = loginPage
    }

    override fun onViewCreated() {

    }
}