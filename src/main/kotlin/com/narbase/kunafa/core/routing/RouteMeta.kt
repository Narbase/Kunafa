package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.lifecycle.Observable

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class RouteMeta(val path: String, val params: Observable<Map<String, String>>) {
    /*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
    var onRouteWillChange: (() -> Boolean)? = null
}
