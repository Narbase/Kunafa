@file:Suppress("unused")

package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.lifecycle.Observable

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class MatchFirstRoute(
        meta: RouteMeta,
        segments: List<RouteSegment>,
        parentRoute: Route?,
        isExact: Boolean
) : Route(meta, segments, parentRoute, isExact) {

    override fun onMatch(windowSegments: List<RouteSegment>) {
        var matchFound = false
        for (child in children) {
            if (matchFound.not() && child.doesMatch(windowSegments)) {
                matchFound = true
                child.update()
            } else {
                child.onUnMatch()
            }
        }
    }

    override fun onUnMatch() {
        children.forEach { it.onUnMatch() }
    }

    private fun executeBody(parentView: View, block: View.() -> Unit) {
        val oldPath = setupRouterToCurrentRoute()
        Router.ignoreRouteUpdate = true
        parentView.block()
        Router.ignoreRouteUpdate = false
        restoreRouterConfig(oldPath)
    }

    companion object {
        fun createRoute(
                parentView: View,
                block: View.() -> Unit
        ): Route {
            val routePath = getPath(Router.currentPath, "/", false)

            val routeSegments = getSegments(routePath)

            val meta = RouteMeta(routePath, Observable())
            val route = MatchFirstRoute(meta, routeSegments, Router.parentRoute, false)
            addToParent(route)
            route.executeBody(parentView, block)
            route.update()
            return route
        }
    }
}


fun View.matchFirst(
        block: View.() -> Unit
) {
    MatchFirstRoute.createRoute(this, block)
}
