package com.narbase.kunafa.core.routing

import kotlin.browser.window

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/03/09.
 */

object Router {
    init {
        window.onpopstate = {
            Router.update()
        }
    }

    var currentPath = "/"
    var parentRoute: Route? = null

    private val rootRoutes = mutableListOf<Route>()

    private fun update() {
        rootRoutes.forEach { route ->
            route.update()
        }
    }

    fun add(route: Route) {
        rootRoutes.add(route)
    }


    fun navigateTo(path: String) {
        window.history.pushState(null, "", "/${path.trimStart('/')}")
        Router.update()
    }

}