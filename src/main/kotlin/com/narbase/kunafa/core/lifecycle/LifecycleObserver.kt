package com.narbase.kunafa.core.lifecycle

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
interface LifecycleObserver {

    fun onViewCreated(lifecycleOwner: LifecycleOwner) {

    }

    fun viewWillMount(lifecycleOwner: LifecycleOwner) {

    }

    fun onViewMounted(lifecycleOwner: LifecycleOwner) {

    }

    fun viewWillBeRemoved(lifecycleOwner: LifecycleOwner) {

    }

    fun onViewRemoved(lifecycleOwner: LifecycleOwner) {

    }

}