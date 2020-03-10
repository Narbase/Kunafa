package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.lifecycle.Observable

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

/**
 * @param path: the route path
 * @param params the route parameters used in :<param>
 */
class RouteMeta(val path: String, val params: Observable<Map<String, String>>) {
    /**
     * Called before the route changes. If the return value is false, then route will not
     * change
     * @return true to allow navigating, and false to prevent navigation away
     */
    var onRouteWillChange: (() -> Boolean)? = null
}
