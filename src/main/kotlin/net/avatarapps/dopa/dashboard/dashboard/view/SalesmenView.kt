package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.DetachedView

/**
 * NARBASE CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] NARBASE
 * All Rights Reserved.
 * Created by islam
 * On: 12/14/17.
 */
class SalesmenView : DashboardPlainViewContent("Salesmen view") {
    override val pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            textView {
                text = "I am in"
            }
        }
    }

}

