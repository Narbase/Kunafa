package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.lifecycle.Observable

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2020/02/10.
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
