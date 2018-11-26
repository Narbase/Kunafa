@file:Suppress("unused")

package com.narbase.kunafa.core.viewcontroller

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

    open fun viewWillBeCreated(view: View) {

    }

    open fun onViewCreated(view: View) {

    }

    open fun viewWillBeRemoved(view: View) {

    }

    open fun onViewRemoved(view: View) {

    }

}