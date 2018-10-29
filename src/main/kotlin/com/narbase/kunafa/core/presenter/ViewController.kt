package com.narbase.kunafa.core.presenter

import com.narbase.kunafa.core.components.View

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/26/17.
 */
abstract class ViewController {
    abstract fun onViewCreated(view: View)
}