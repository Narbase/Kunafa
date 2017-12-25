package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color

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
            verticalLayout {
                width = wrapContent
                height = wrapContent
                padding = 8.px
                background = DopaColors.main

                textView {
                    text = "+ Add salesman"
                    textColor = Color.white
                    width = wrapContent
                }
            }
        }
    }
}

