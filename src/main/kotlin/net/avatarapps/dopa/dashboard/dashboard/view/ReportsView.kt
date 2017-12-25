package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.textView

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 12/14/17.
 */
class ReportsView : DashboardPlainViewContent("Reports view") {
    override val pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            textView {
                text = "Reports view"
            }
        }
    }
}