@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.components.Anchor
import com.narbase.kunafa.core.components.Component
import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.a
import kotlin.browser.window

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

abstract class Route constructor(
        val meta: RouteMeta,
        val segments: List<RouteSegment>,
        val parentRoute: Route?,
        val isExact: Boolean
) {

    protected val children = mutableListOf<Route>()

    val path
        get() = "/${segments.joinToString("/")}"
    val isRootRoute
        get() = parentRoute == null

    fun update() {
        if (Router.ignoreRouteUpdate) {
            return
        }
        var shouldRetry: Boolean
        var redirectCounter = 0
        do {
            val oldPath = setupRouterToCurrentRoute()
            if (isRootRoute.not()) {
                // check if matching without try/catch block. Let a parent rootRoute catch it
                checkIfMatching()
                shouldRetry = false
            } else try {
                checkIfMatching()
                shouldRetry = false
            } catch (e: RedirectException) {
                redirectCounter++
                if (redirectCounter < Router.MAX_REDIRECT_LIMIT) {
                    shouldRetry = true
                } else {
                    shouldRetry = false
                    console.log("Maximum redirect limit 100 is reached. Please check if you are redirecting correctly.")
                }
            }

            restoreRouterConfig(oldPath)
        } while (shouldRetry)

    }

    private fun checkIfMatching() {
        val windowSegments = getSegments(window.location.pathname)
        if (doesMatch(windowSegments)) {
            Router.onRouteMatch(this)
            updatePathParams(windowSegments)
            onMatch(windowSegments)
        } else {
            onUnMatch()
        }
    }

    abstract fun onMatch(windowSegments: List<RouteSegment>)

    open fun onUnMatch() {
        children.forEach {
            Router.onRouteUnMatch(it)
            it.onUnMatch()
        }
        Router.onRouteUnMatch(this)
    }

    protected fun restoreRouterConfig(oldPath: String) {
        Router.parentRoute = parentRoute
        Router.currentPath = oldPath
        Router.isUpdating = false
    }

    protected fun setupRouterToCurrentRoute(): String {
        val oldPath = Router.currentPath
        Router.isUpdating = true
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

    fun doesMatch(windowSegments: List<RouteSegment>): Boolean {
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

        protected fun addToParent(route: Route) {
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
): ComponentRoute = ComponentRoute.createComponentRoute(this, path, isExact, isAbsolute, block)

open class RouteSegment(val text: String) {
    open fun matches(route: RouteSegment?) = text == route?.text
    override fun toString() = text
}

class ParameterSegment(text: String) : RouteSegment(text) {
    override fun matches(route: RouteSegment?) = route != null
    override fun toString() = ":$text"
}

fun View?.link(path: String, block: (Anchor.() -> Unit)? = null) = a {
    val completePath = Route.getPath(Router.currentPath, path, isAbsolute = true)
    href = completePath
    onClick = {
        it.preventDefault()
        Router.navigateTo(completePath)
    }
    block?.invoke(this)
}
