@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.components.BaseElement
import com.narbase.kunafa.core.components.Component
import com.narbase.kunafa.core.components.View

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/05/30.
 */
class ComponentRoute(
        meta: RouteMeta,
        segments: List<RouteSegment>,
        val component: Component,
        parentRoute: Route?,
        val parentView: BaseElement?,
        private val referenceView: BaseElement,
        isExact: Boolean
) : Route(meta, segments, parentRoute, isExact) {
    override fun onMatch(windowSegments: List<RouteSegment>) {
        parentView?.mountAfter(component, referenceView)
        updatePathParams(windowSegments)
        children.forEach {
            it.update()
        }
    }

    override fun onUnMatch() {
        parentView?.unMount(component)
    }

}