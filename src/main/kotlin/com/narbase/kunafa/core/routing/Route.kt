@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.lifecycle.Observable
import kotlin.browser.window

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/03/09.
 */

class Route(
        val meta: RouteMeta,
        val segments: List<RouteSegment>,
        val component: Component,
        val parentRoute: Route?,
        val parentView: View?,
        val referenceView: View,
        val isExact: Boolean
) {

    private val children = mutableListOf<Route>()

    val path
        get() = "/${segments.joinToString("/")}"

    fun update() {
        val oldPath = setupRouterToCurrentRoute()
        val windowSegments = getSegments(window.location.pathname)
        if (doesMatch(windowSegments)) {
            parentView?.mountAfter(component, referenceView)
            updatePathParams(windowSegments)
            children.forEach {
                it.update()
            }
        } else {
            parentView?.unMount(component)
        }
        restoreRouterConfig(oldPath)
    }

    private fun restoreRouterConfig(oldPath: String) {
        Router.parentRoute = parentRoute
        Router.currentPath = oldPath
    }

    private fun setupRouterToCurrentRoute(): String {
        val oldPath = Router.currentPath
        Router.currentPath = meta.path
        Router.parentRoute = this
        return oldPath
    }

    private fun updatePathParams(windowSegments: List<RouteSegment>) {
        val params = mutableMapOf<String, String>()
        segments
                .forEachIndexed { index, segment ->
                    if ((segment is ParameterSegment).not())
                        return@forEachIndexed
                    val s = segment as? ParameterSegment
                    val windowSegment = windowSegments[index].text
                    params[s?.text ?: ""] = windowSegment
                }
        if (params.isNotEmpty()) {
            meta.params.value = params
        }
    }

    fun add(route: Route) {
        children.add(route)
    }

    private fun doesMatch(windowSegments: List<RouteSegment>): Boolean {
        when {
            isExact -> if (segments.size != windowSegments.size) return false
            else -> if (segments.size > windowSegments.size) return false
        }
        segments.forEachIndexed { index, segment ->
            if (segment.matches(windowSegments.getOrNull(index)).not())
                return false
        }
        return true
    }

    companion object {
        fun createRoute(
                parentView: View,
                path: String,
                isExact: Boolean = false,
                isAbsolute: Boolean = false,
                block: (meta: RouteMeta) -> Component
        ): Route {
            val routePath = getPath(Router.currentPath, path, isAbsolute)

            val routeSegments = getSegments(routePath)

            val reference = parentView.view { isVisible = false }
            val meta = RouteMeta(routePath, Observable())
            val component = block(meta)
            val route = Route(meta, routeSegments, component, Router.parentRoute, parentView, reference, isExact)
            addToParent(route)
            route.update()
            return route
        }

        private fun addToParent(route: Route) {
            if (Router.parentRoute == null) {
                Router.add(route)
            } else {
                Router.parentRoute?.add(route)
            }
        }

        fun getPath(currentPath: String, path: String, isAbsolute: Boolean): String {
            val trimmedCurrentPath = currentPath.trim('/')
            return when {
                isAbsolute || trimmedCurrentPath.isBlank() -> "/${path.trim('/')}"
                else -> "/$trimmedCurrentPath/${path.trim('/')}"
            }
        }

        fun getComponent(meta: RouteMeta, block: View?.(meta: RouteMeta) -> View): Component = object : Component() {
            override fun View?.getView() = block(meta)
        }

        fun getSegments(currentPath: String): List<RouteSegment> {
            val stringSegments = currentPath.split('/').filter { it.isNotBlank() }
            return stringSegments.map {
                if (it.startsWith(":")) {
                    ParameterSegment(it.trim(':'))
                } else {
                    RouteSegment(it)
                }
            }
        }

    }
}

fun View.route(
        path: String,
        isExact: Boolean = false,
        isAbsolute: Boolean = false,
        block: View?.(meta: RouteMeta) -> View
) = routeComponent(path, isExact, isAbsolute) { meta -> Route.getComponent(meta, block) }

fun View.routeComponent(
        path: String,
        isExact: Boolean = false,
        isAbsolute: Boolean = false,
        block: (meta: RouteMeta) -> Component
): Route = Route.createRoute(this, path, isExact, isAbsolute, block)

open class RouteSegment(val text: String) {
    open fun matches(route: RouteSegment?) = text == route?.text
    override fun toString() = text
}

class ParameterSegment(text: String) : RouteSegment(text) {
    override fun matches(route: RouteSegment?) = route != null
    override fun toString() = ":$text"
}

class RouteMeta(val path: String, val params: Observable<Map<String, String>>)

fun View?.link(path: String, block: (Anchor.() -> Unit)? = null) = a {
    val completePath = Route.getPath(Router.currentPath, path, isAbsolute = true)
    href = completePath
    onClick = {
        it.preventDefault()
        Router.navigateTo(completePath)
    }
    block?.invoke(this)
}
