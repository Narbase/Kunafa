@file:Suppress("unused")

package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.lifecycle.Observable

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/05/30.
 */
class MatchFirstRoute(
        meta: RouteMeta,
        segments: List<RouteSegment>,
        parentRoute: Route?,
        isExact: Boolean
) : Route(meta, segments, parentRoute, isExact) {

    override fun onMatch(windowSegments: List<RouteSegment>) {
        children.forEach { it.onUnMatch() }
        for (child in children) {
            if (child.doesMatch(windowSegments)) {
                child.update()
                break
            }
        }
    }

    override fun onUnMatch() {
        children.forEach { it.onUnMatch() }
    }

    private fun executeBody(parentView: View, block: View.() -> Unit) {
        val oldPath = setupRouterToCurrentRoute()
        parentView.block()
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
