package net.avatarapps.dopa.dashboard.dashboard.view.salesmen

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.dopa.dashboard.main
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.weightOf
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

    override val plainPresenter = SalesmenPresenter()
    override var pageViewContent: ViewContent = plainPresenter.salesmenListView
}

class SalesmenListView(private val salesmenPresenter: SalesmenPresenter) : ViewContent() {
    override fun DetachedView.contentDefinition() {

        verticalLayout {
            presenter = salesmenPresenter
            width = wrapContent
            height = wrapContent

            salesmenPresenter.addSalesmanButton = textView {
                width = wrapContent
                height = wrapContent
                padding = 8.px
                background = DopaColors.main
                text = "+ Add salesman"
                textColor = Color.white
                width = wrapContent
            }

            textView {
                text = "No salesmen were added yet."
                textColor = DopaColors.separatorLight
                textSize = 24.px
                width = matchParent
                textAlign = TextView.TextAlign.Center
                marginTop = 16.px
            }

        }
    }
}

class AddSalesmanView(private val salesmenPresenter: SalesmenPresenter) : ViewContent() {
    override fun DetachedView.contentDefinition() {
        val formWidth = 640.px

        verticalLayout {
            presenter = salesmenPresenter
            width = matchParent
            alignItems = Alignment.Center
            height = wrapContent

            textInput {
                placeholder = "Full name"
                width = matchParent
                textSize = 18.px
                marginBottom = 12.px
                maxWidth = formWidth
            }

            textInput {
                placeholder = "Username"
                width = matchParent
                textSize = 18.px
                marginBottom = 12.px
                maxWidth = formWidth
            }

            textInput {
                placeholder = "Password"
                width = matchParent
                textSize = 18.px
                marginBottom = 12.px
                maxWidth = formWidth
            }

            textInput {
                placeholder = "Phone number"
                width = matchParent
                textSize = 18.px
                marginBottom = 12.px
                maxWidth = formWidth
            }

            horizontalLayout {
                width = matchParent
                maxWidth = formWidth

                salesmenPresenter.cancelAddSalesmanButton = textView {
                    padding = 8.px
                    width = weightOf(1)
                    height = wrapContent
                    textColor = Color.white
                    text = "Cancel"
                    background = DopaColors.redLight
                    width = wrapContent
                    marginEnd = 18.px
                    textAlign = TextView.TextAlign.Center
                }

                salesmenPresenter.saveNewSalesmanButton = textView {
                    padding = 8.px
                    width = weightOf(1)
                    height = wrapContent
                    textColor = Color.white
                    text = "Save new salesman"
                    background = DopaColors.greenLight
                    width = wrapContent
                    textAlign = TextView.TextAlign.Center
                }

            }

        }
    }
}

