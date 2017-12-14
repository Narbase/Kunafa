package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.textView

/**
 * NARBASE CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] NARBASE
 * All Rights Reserved.
 * Created by islam
 * On: 12/14/17.
 */
class SalesmenView : ViewContent() {
    override fun DetachedView.contentDefinition() {
        textView {
            text = "Salesmen View"
        }
    }
}