package net.avatarapps.dopa.dashboard.dashboard.view.salesmen

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.layout.JustifyContent
import net.avatarapps.kunafa.core.components.layout.LinearLayout
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
            width = matchParent
            height = wrapContent

            salesmenPresenter.addSalesmanButton = textView {
                width = wrapContent
                height = wrapContent
                padding = 8.px
                background = DopaColors.main
                text = "+ Add salesman"
                textColor = Color.white
                width = wrapContent
                onClick = { salesmenPresenter.onAddSalesmanButtonClicked() }
            }

            salesmenPresenter.noSalesmenTextView = textView {
                text = "No salesmen were added yet."
                textColor = DopaColors.separatorLight
                textSize = 24.px
                width = matchParent
                textAlign = TextView.TextAlign.Center
                marginTop = 16.px
                isVisible = false
            }

            salesmenPresenter.salesmenListLoadingImageView = loadingIndicator()

            salesmenPresenter.salesmenList = verticalLayout {
                width = matchParent
                height = matchParent
                isScrollableVertically = false

            }
        }
    }



}
fun LinearLayout.addSalesman(salesman: SalesmanDs, salesmenPresenter: SalesmenPresenter) {
    verticalLayout {
        width = matchParent
        horizontalLayout {
            width = matchParent
            marginTop = 16.px
            padding = 16.px
            background = Color.white
            justifyContent = JustifyContent.SpaceBetween

            verticalLayout {
                textView {
                    text = salesman.name
                    textColor = DopaColors.main
                    textSize = 18.px
                }

                textView {
                    text = salesman.phone
                    textColor = DopaColors.mainLight
                    marginTop = 6.px
                }
            }

            textView {
                text = "Edit"
                background = DopaColors.mainLight
                textColor = Color.white
                padding = 8.px
                alignSelf = Alignment.Center
                onClick = { salesmenPresenter.onEditSalesman(salesman) }
            }
        }

        view {
            width = matchParent
            height = 2.px
            background = DopaColors.separatorLight
        }
    }
}

class AddSalesmanView(private val salesmenPresenter: SalesmenPresenter) : ViewContent() {
    override fun DetachedView.contentDefinition() {
        val formWidth = 640.px

        verticalLayout {
            width = matchParent
            alignItems = Alignment.Center
            height = wrapContent

            salesmenPresenter.name = textInput {
                placeholder = "Full name"
                width = matchParent
                textSize = 18.px
                marginBottom = 12.px
                maxWidth = formWidth
            }

            salesmenPresenter.username = textInput {
                placeholder = "Username"
                width = matchParent
                textSize = 18.px
                marginBottom = 12.px
                maxWidth = formWidth
            }

            salesmenPresenter.password = textInput {
                placeholder = "Password"
                width = matchParent
                textSize = 18.px
                marginBottom = 12.px
                maxWidth = formWidth
            }

            salesmenPresenter.phone = textInput {
                placeholder = "Phone number"
                width = matchParent
                textSize = 18.px
                marginBottom = 12.px
                maxWidth = formWidth
            }

            salesmenPresenter.addSalesmanControlView = horizontalLayout {
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
                    onClick = { salesmenPresenter.onCancelAddSalesmanButton() }
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
                    onClick = { salesmenPresenter.onSaveNewSalesmanButtonClicked() }
                }

            }
            salesmenPresenter.addSalesmenLoadingImageView = loadingIndicator()

            salesmenPresenter.addSalesmanStatusText = textView {
                text = ""
                textColor = DopaColors.redLight
                textSize = 14.px
                width = matchParent
                textAlign = TextView.TextAlign.Center
                marginTop = 8.px
                isVisible = false
            }

        }
    }
}

private fun LinearLayout.loadingIndicator(): ImageView {
    return imageView {
        marginTop = 18.px
        width = 40.px
        height = 40.px
        alignSelf = Alignment.Center
        isVisible = false
        img.src = "/public/img/loading.gif"
    }
}