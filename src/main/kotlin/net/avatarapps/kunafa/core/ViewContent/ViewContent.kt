package net.avatarapps.kunafa.core.ViewContent

import net.avatarapps.kunafa.core.components.detachedView
import net.avatarapps.kunafa.core.components.layout.DetachedView

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