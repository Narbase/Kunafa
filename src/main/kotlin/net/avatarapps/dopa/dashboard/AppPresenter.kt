package net.avatarapps.dopa.dashboard

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardNavigator
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPageContent
import net.avatarapps.dopa.dashboard.login.LoginPageContent
import net.avatarapps.dopa.dashboard.login.LoginPageNavigator
import net.avatarapps.dopa.dashboard.storage.StorageManager
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.ViewContainer
import net.avatarapps.kunafa.core.presenter.Presenter

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 12/12/17.
 */
class AppPresenter(
) : Presenter(), LoginPageNavigator, DashboardNavigator {

    var loginPage: LoginPageContent? = null
    var dashboardPage: DashboardPageContent? = null
    private var mainView: ViewContainer? = null

    override fun onLoggedInSuccessful() {
        StorageManager.setUserLoggedIn(true)
        mainView?.content = dashboardPage
    }

    override fun onLogoutSelected() {
        StorageManager.setUserLoggedIn(false)
        mainView?.content = loginPage
    }

    override fun onViewCreated(view: View) {
        mainView = view as? ViewContainer
        if (StorageManager.isUserLoggedIn()) {
            mainView?.content = dashboardPage
        } else {
            mainView?.content = loginPage
        }
    }
}