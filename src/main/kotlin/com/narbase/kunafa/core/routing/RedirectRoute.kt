@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.lifecycle.Observable

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class RedirectRoute(
        val redirectPath: String,
        meta: RouteMeta,
        segments: List<RouteSegment>,
        parentRoute: Route?,
        isExact: Boolean,
        val isAbsoluteDestination: Boolean
) : Route(meta, segments, parentRoute, isExact) {
    override fun onMatch(windowSegments: List<RouteSegment>) {
        val completeRedirectPath = if (isAbsoluteDestination) {
            redirectPath
        } else {
            val windowPath = "/${windowSegments.joinToString("/")}"
            "$windowPath/${redirectPath.trim('/')}"
        }
        Router.navigateTo(completeRedirectPath, replaceCurrentState = true)
    }

    override fun onUnMatch() {

    }

    companion object {
        fun createRoute(
                redirectPath: String,
                path: String,
                isExact: Boolean,
                isAbsolute: Boolean,
                isAbsoluteDestination: Boolean
        ): Route {
            val routePath = getPath(Router.currentPath, path, isAbsolute)

            val routeSegments = getSegments(routePath)
            val meta = RouteMeta(routePath, Observable())
            val route = RedirectRoute(redirectPath, meta, routeSegments, Router.parentRoute, isExact, isAbsoluteDestination)
            addToParent(route)
            route.update()
            return route
        }
    }


}

fun View.redirect(
        to: String,
        isAbsoluteDestination: Boolean = false,
        from: String = "/",
        isExact: Boolean = true,
        isAbsolute: Boolean = false
) {
    RedirectRoute.createRoute(to, from, isExact, isAbsolute, isAbsoluteDestination)
}
