package com.narbase.kunafa.core.viewcontroller

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/03/04.
 */
interface LifecycleObserver {

    fun viewWillMount(lifecycleOwner: LifecycleOwner)

    fun onViewMounted(lifecycleOwner: LifecycleOwner)

    fun viewWillBeRemoved(lifecycleOwner: LifecycleOwner)

    fun onViewRemoved(lifecycleOwner: LifecycleOwner)

}