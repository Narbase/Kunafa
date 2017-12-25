package net.avatarapps.dopa.dashboard.storage

import org.w3c.dom.get
import kotlin.browser.window

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 12/14/17.
 */
object StorageManager {

    private const val LOGGED_IN = "LOGGED_IN"

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        window.localStorage.setItem(LOGGED_IN, if (isLoggedIn) "true" else "false")
    }

    fun isUserLoggedIn(): Boolean {
        return window.localStorage.get(LOGGED_IN) == "true"
    }



}