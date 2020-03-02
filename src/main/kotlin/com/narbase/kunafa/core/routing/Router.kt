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

@Suppress("unused")
object Router {
    init {
        window.onpopstate = {
            update()
        }
    }

    var currentPath = "/"
    var parentRoute: Route? = null
    var isUpdating = false
    private val matchedRoutes = mutableSetOf<Route>()
    private val rootRoutes = mutableListOf<Route>()
    const val MAX_REDIRECT_LIMIT = 100

    fun onRouteMatch(route: Route) {
        matchedRoutes.add(route)
    }

    fun onRouteUnMatch(route: Route) {
        matchedRoutes.remove(route)
    }

    fun invalidateCache() {
        rootRoutes.clear()
    }

    @Suppress("LiftReturnOrAssignment")
    private fun update() {
        rootRoutes.forEach { route ->
            route.update()
        }
    }

    fun add(route: Route) {
        rootRoutes.add(route)
    }


    fun navigateTo(path: String) {
        val shouldPrevent = matchedRoutes.firstOrNull { it.meta.onRouteWillChange?.invoke() == false } != null
        if (shouldPrevent) return
        window.history.pushState(null, "", "/${path.trimStart('/')}")
        if (isUpdating) throw RedirectException()
        else update()
    }

}