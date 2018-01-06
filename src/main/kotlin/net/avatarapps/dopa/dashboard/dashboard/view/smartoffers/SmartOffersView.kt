package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.dopa.dashboard.dashboard.view.zones.ZoneDs
import net.avatarapps.dopa.dashboard.dashboard.view.zones.makeClickable
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
class SmartOffersView : DashboardPlainViewContent("Smart offers management") {

    override val plainPresenter = SmartOffersPresenter()
    override var pageViewContent: ViewContent = plainPresenter.smartOffersListView
}

class SmartOffersListView(private val smartOffersPresenter: SmartOffersPresenter) : ViewContent() {
    override fun DetachedView.contentDefinition() {

        verticalLayout {
            width = matchParent
            height = wrapContent

            smartOffersPresenter.addSmartOfferButton = textView {

                width = wrapContent
                height = wrapContent
                padding = 8.px
                background = DopaColors.main
                text = "+ Add smartOffer"
                textColor = Color.white
                width = wrapContent
                onClick = { smartOffersPresenter.onAddSmartOfferButtonClicked() }
                makeClickable()
            }

            smartOffersPresenter.noSmartOffersTextView = textView {
                text = "No smartOffers were added yet."
                textColor = DopaColors.separatorLight
                textSize = 24.px
                width = matchParent
                textAlign = TextView.TextAlign.Center
                marginTop = 16.px
                isVisible = false
            }

            smartOffersPresenter.smartOffersListLoadingImageView = loadingIndicator()

            smartOffersPresenter.smartOffersList = verticalLayout {
                width = matchParent
                height = matchParent
                isScrollableVertically = false

            }
        }
    }
}

fun TextView.makeClickable(backgroundColor: Color = DopaColors.main) {
    element.onmouseover = {
        background = DopaColors.mainLight
        element.style.cursor = "pointer"
        asDynamic()
    }
    element.onmouseleave = {
        background = backgroundColor
        element.style.cursor = ""
        asDynamic()

    }
}

fun LinearLayout.addSmartOffer(smartOffer: SmartOfferDs, smartOffersPresenter: SmartOffersPresenter) {
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
                    text = smartOffer.name
                    textColor = DopaColors.main
                    textSize = 18.px
                }

                textView {
                    text = smartOffer.phone
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
                onClick = { smartOffersPresenter.onEditSmartOffer(smartOffer) }
                makeClickable()
            }
        }

        view {
            width = matchParent
            height = 2.px
            background = DopaColors.separatorLight
        }
    }
}

class AddSmartOfferView(private val smartOffersPresenter: SmartOffersPresenter) : ViewContent() {
    override fun DetachedView.contentDefinition() {
        val formWidth = 640.px

        verticalLayout {
            width = matchParent
            height = wrapContent
            alignItems = Alignment.Center

            verticalLayout {
                width = matchParent
                height = wrapContent
                alignItems = Alignment.Center
                maxWidth = formWidth

                smartOffersPresenter.name = textInput {
                    placeholder = "Drug name"
                    width = matchParent
                    textSize = 18.px
                    marginBottom = 12.px
                    maxWidth = formWidth
                }

                smartOffersPresenter.username = textInput {
                    placeholder = "Description"
                    width = matchParent
                    textSize = 18.px
                    marginBottom = 12.px
                    maxWidth = formWidth
                }

                addTarget("All")
                addTarget("Zone")
                addTarget("Label")
                addTarget("All pharmacies with previous interactions")

                smartOffersPresenter.addSmartOfferControlView = horizontalLayout {
                    width = matchParent
                    maxWidth = formWidth
                    marginTop = 12.px

                    smartOffersPresenter.cancelAddSmartOfferButton = textView {
                        padding = 8.px
                        width = weightOf(1)
                        height = wrapContent
                        textColor = Color.white
                        text = "Cancel"
                        background = DopaColors.redLight

                        makeClickable(DopaColors.redLight)
                        width = wrapContent
                        marginEnd = 18.px
                        textAlign = TextView.TextAlign.Center
                        onClick = { smartOffersPresenter.onCancelAddSmartOfferButton() }
                    }

                    smartOffersPresenter.saveNewSmartOfferButton = textView {
                        padding = 8.px
                        width = weightOf(1)
                        height = wrapContent
                        textColor = Color.white
                        text = "Save new smart offer"
                        background = DopaColors.greenLight
                        makeClickable(DopaColors.greenLight)
                        width = wrapContent
                        textAlign = TextView.TextAlign.Center
                        onClick = { smartOffersPresenter.onSaveNewSmartOfferButtonClicked() }
                    }

                }
                smartOffersPresenter.addSmartOffersLoadingImageView = loadingIndicator()

                smartOffersPresenter.addSmartOfferStatusText = textView {
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
}

fun LinearLayout.addTarget(name: String): Checkbox? {
    var checkbox: Checkbox? = null
    horizontalLayout {
        width = matchParent
        marginTop = 4.px
        padding = 4.px
        alignItems = Alignment.Center
        checkbox = checkbox {
            margin = 8.px
        }
        textView {
            text = name
            textColor = DopaColors.main
            width = matchParent
            textAlign = TextView.TextAlign.Left
            textSize = 18.px
        }
    }
    return checkbox
}


fun LinearLayout.loadingIndicator(): ImageView {
    return imageView {
        marginTop = 18.px
        width = 40.px
        height = 40.px
        alignSelf = Alignment.Center
        isVisible = false
        img.src = "/public/img/loading.gif"
    }
}