package com.narbase.kunafa.core.ViewContent

import com.narbase.kunafa.core.components.detachedView
import com.narbase.kunafa.core.components.layout.DetachedView

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 12/12/17.
 */
abstract class ViewContent {
    abstract fun DetachedView.contentDefinition()

    val content: DetachedView
    get() {
        return detachedView {
            contentDefinition()
        }
    }

}