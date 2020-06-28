package com.narbase.kunafa.core.routing

import kotlin.browser.window

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
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

    /**
     * When updating, navigateTo should thrown an exception. Otherwise, it will invoke the update function.
     */
    var isUpdating = false

    /**
     * This is used to prevent matchFirst children from calling update(), as matchFirst will call update itself and only
     * mount the first child.
     */
    var ignoreRouteUpdate = false

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


    fun navigateTo(path: String, replaceCurrentState: Boolean = false) {
        val shouldPrevent = matchedRoutes.firstOrNull { it.meta.onRouteWillChange?.invoke() == false } != null
        if (shouldPrevent) return
        if (replaceCurrentState) {
            window.history.replaceState(null, "", "/${path.trimStart('/')}")
        } else {
            window.history.pushState(null, "", "/${path.trimStart('/')}")
        }
        if (isUpdating) throw RedirectException()
        else update()
    }

}