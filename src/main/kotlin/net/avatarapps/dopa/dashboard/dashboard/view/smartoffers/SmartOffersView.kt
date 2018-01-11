package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.common.DopaColors.redLight
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
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
import org.w3c.dom.events.Event

/**
 * NARBASE CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] NARBASE
 * All Rights Reserved.
 * Created by islam
 * On: 12/14/17.
 */
class SmartOffersView : DashboardPlainViewContent("Smart offers management") {

    override val plainPresenter = SmartOffersPresenter(this)

    private val smartOffersListView = SmartOffersListView(plainPresenter)
    private val addSmartOfferPresenter = AddSmartOfferPresenter(plainPresenter)

    private val addSmartOfferView = AddSmartOfferView(addSmartOfferPresenter)

    override var pageViewContent: ViewContent = smartOffersListView

    fun setViewToOffersList() {
        plainPresenter.mainViewContent?.content = smartOffersListView
    }

    fun setViewToAddOffer() {
        plainPresenter.mainViewContent?.content = addSmartOfferView
    }
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
                    text = smartOffer.drugName
                    textColor = DopaColors.main
                    textSize = 18.px
                }

                textView {
                    text = smartOffer.offerDescription
                    textColor = DopaColors.mainLight
                    marginTop = 6.px
                }

                addTargetText("Target: ")
                if (smartOffer.targetIsAll) {
                    addTargetText(" \t- All pharmacies")
                } else {
                    if (smartOffer.targetContainsLabels) {
                        var target = "\t- Labels: "
                        smartOffer.targetedLabels?.forEach { target += "$it, " }
                        target = target.trimEnd().trimEnd(',')
                        addTargetText(target)
                    }
                    if (smartOffer.targetContainsZones) {
                        var target = "\t- Zones: "
                        smartOffer.targetedZonesNames.forEach { target += "$it, " }
                        target = target.trimEnd().trimEnd(',')
                        addTargetText(target)
                    }
                    if (smartOffer.targetContainsPharmaciesWithPreviousInteractions) {
                        val target = "\t- Pharmacies with previous interactions"
                        addTargetText(target)
                    }
                }
            }

            val removeButton = textView {
                text = "Delete"
                background = DopaColors.redLight
                textColor = Color.white
                padding = 8.px
                alignSelf = Alignment.Start
                element.onmouseover = {
                    background = DopaColors.mainLight
                    element.style.cursor = "pointer"
                    asDynamic()
                }
                element.onmouseleave = {
                    background = redLight
                    element.style.cursor = ""
                    asDynamic()
                }
            }
            removeButton.onClick = { smartOffersPresenter.onRemoveSmartOffer(smartOffer, removeButton) }
        }

        view {
            width = matchParent
            height = 2.px
            background = DopaColors.separatorLight
        }
    }
}

private fun LinearLayout.addTargetText(target: String) {
    textView {
        text = target
        textColor = DopaColors.mainLight
        marginTop = 6.px
    }
}

class AddSmartOfferView(private val addSmartOfferPresenter: AddSmartOfferPresenter) : ViewContent() {
    init {
        addSmartOfferPresenter.view = this
    }

    private var zonesList: LinearLayout? = null
    private var zonesListLoadingImageView: ImageView? = null
    var noZonesTextView: TextView? = null


    var allCheckbox: Checkbox? = null
    var labelsCheckbox: Checkbox? = null
    var zonesCheckbox: Checkbox? = null
    var previousPharmaciesCheckbox: Checkbox? = null

    private var otherTargetsLayout: LinearLayout? = null

    private var labelsLayout: LinearLayout? = null
    var labelACheckbox: Checkbox? = null
    var labelBCheckbox: Checkbox? = null
    var labelCCheckbox: Checkbox? = null
    var labelDCheckbox: Checkbox? = null
    var labelECheckbox: Checkbox? = null

    override fun DetachedView.contentDefinition() {
        val formWidth = 640.px

        verticalLayout {
            width = matchParent
            height = wrapContent
            alignItems = Alignment.Center
            presenter = addSmartOfferPresenter

            verticalLayout {
                width = formWidth
                height = wrapContent
                alignItems = Alignment.Center
                maxWidth = formWidth


                addSmartOfferPresenter.drugNameText = textView {
                    text = "Click to Add drug"
                    width = matchParent
                    textSize = 18.px
                    textColor = DopaColors.mainLight
                    padding = 6.px

                    element.onmouseover = {
                        background = Color.rgb(220, 220, 220)
                        element.style.cursor = "pointer"
                        asDynamic()
                    }
                    element.onmouseleave = {
                        background = Color.transparent
                        element.style.cursor = ""
                        asDynamic()

                    }

                }

                addSmartOfferPresenter.drugNameTextInput = textInput {
                    placeholder = "Drug name"
                    width = matchParent
                    textSize = 18.px
                    isVisible = false
                }

                anchorLayout {
                    width = formWidth
                    height = 0.px
                    addSmartOfferPresenter.suggestionListLayout = verticalLayout {
                        width = matchParent
                        height = wrapContent
                        element.style.position = "relative"
                        element.style.zIndex = "3"
                        background = Color.white
                        isVisible = false
                        element.style.boxShadow = "0px 2px 10px 1px #00000073"

                    }
                }


                addSmartOfferPresenter.descriptionTextInput = textInput {
                    placeholder = "Description"
                    width = matchParent
                    textSize = 18.px
                    marginTop = 12.px
                    marginBottom = 12.px
                }
                textView {
                    text = "Targets"
                    alignSelf = Alignment.Start
                    textColor = DopaColors.mainDark
                    textSize = 18.px
                    marginTop = 16.px
                }

                allCheckbox = addTarget("All pharmacies") {
                        onAllCheckboxIsClicked(allCheckbox?.isChecked == true)
                }

                otherTargetsLayout = verticalLayout {
                    width = matchParent
                    zonesCheckbox = addTarget("Custom zones") {
                        if (zonesCheckbox?.isChecked == true) {
                            showZonesLayout()
                        } else {
                            hideZonesLayout()
                        }
                    }

                    zonesList = verticalLayout {
                        width = matchParent
                        paddingStart = 24.px
                        isVisible = false
                        zonesListLoadingImageView = loadingIndicator()
                        noZonesTextView = textView {
                            text = "No zones."
                            alignSelf = Alignment.Start
                            textColor = DopaColors.mainDark
                            textSize = 14.px
                            marginTop = 16.px
                        }


                    }
                    labelsCheckbox = addTarget("Custom labels") {
                        if (labelsCheckbox?.isChecked == true) {
                            showLabelsLayout()
                        } else {
                            hideLabelsLayout()
                        }
                    }

                    labelsLayout = verticalLayout {
                        width = matchParent
                        paddingStart = 24.px
                        isVisible = false
                        labelACheckbox = addLabeldCheckbox("A")
                        labelBCheckbox = addLabeldCheckbox("B")
                        labelCCheckbox = addLabeldCheckbox("C")
                        labelDCheckbox = addLabeldCheckbox("D")
                        labelECheckbox = addLabeldCheckbox("E")

                    }

                    previousPharmaciesCheckbox = addTarget("All pharmacies with previous interactions"){}

                }


                addSmartOfferPresenter.addSmartOfferControlView = horizontalLayout {
                    width = matchParent
                    maxWidth = formWidth
                    marginTop = 12.px

                    addSmartOfferPresenter.cancelAddSmartOfferButton = textView {
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
                        onClick = { addSmartOfferPresenter.onCancelAddSmartOfferButton() }
                    }

                    addSmartOfferPresenter.saveNewSmartOfferButton = textView {
                        padding = 8.px
                        width = weightOf(1)
                        height = wrapContent
                        textColor = Color.white
                        text = "Save new smart offer"
                        background = DopaColors.greenLight
                        makeClickable(DopaColors.greenLight)
                        width = wrapContent
                        textAlign = TextView.TextAlign.Center
                        onClick = {
                            var targetedLabels = ""
                            if (labelACheckbox?.isChecked == true) targetedLabels +="A"
                            if (labelBCheckbox?.isChecked == true) targetedLabels +="B"
                            if (labelCCheckbox?.isChecked == true) targetedLabels +="C"
                            if (labelDCheckbox?.isChecked == true) targetedLabels +="D"
                            if (labelECheckbox?.isChecked == true) targetedLabels +="E"
                            addSmartOfferPresenter.onSaveNewSmartOfferButtonClicked(
                                targetIsAll =  allCheckbox?.isChecked == true,
                                targetContainsZones = zonesCheckbox?.isChecked == true,
                                targetContainsLabels = labelsCheckbox?.isChecked == true,
                                targetContainsPharmaciesWithPreviousInteractions = previousPharmaciesCheckbox?.isChecked == true,
                                targetedLabels = targetedLabels
                        ) }
                    }

                }
                addSmartOfferPresenter.addSmartOffersLoadingImageView = loadingIndicator()

                addSmartOfferPresenter.addSmartOfferStatusText = textView {
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

    private fun onAllCheckboxIsClicked(isChecked: Boolean) {
        otherTargetsLayout?.isVisible = isChecked.not()
    }

    private fun LinearLayout.addDrugSuggestion(drugId: Int, drugName: String) {
        textView {
            width = matchParent
            text = drugName
            textSize = 18.px
            textColor = DopaColors.text
            padding = 4.px
            paddingTop = 6.px
            paddingBottom = 6.px

            element.onmouseover = {
                background = Color.rgb(240, 240, 240)
                element.style.cursor = "pointer"
                asDynamic()
            }
            element.onmouseleave = {
                background = Color.transparent
                element.style.cursor = ""
                asDynamic()

            }

            onClick = { addSmartOfferPresenter.onDrugSelected(drugId, drugName) }
        }
    }

    fun onSearchResultReady(drugs: Map<Int, String>) {
        addSmartOfferPresenter.suggestionListLayout?.clearAllChildren()
        drugs.forEach {
            addSmartOfferPresenter.suggestionListLayout?.addDrugSuggestion(it.key, it.value)
        }
    }

    fun showLabelsLayout(){
        labelsLayout?.isVisible = true
    }

    fun hideLabelsLayout(){
        labelsLayout?.isVisible = false
    }

    fun showZonesLayout(){
        zonesList?.isVisible = true
    }

    fun hideZonesLayout(){
        zonesList?.isVisible = false
    }

    fun showZonesLoadingImage(){
        zonesListLoadingImageView?.isVisible = true
        noZonesTextView?.isVisible = false
    }

    fun hideZonesLoadingImage(){
        zonesListLoadingImageView?.isVisible = false
        noZonesTextView?.isVisible = false
    }

    fun showNoZonesText() {
        noZonesTextView?.isVisible = true
        zonesListLoadingImageView?.isVisible = false
    }

    fun addZoneCheckbox(label: String): Checkbox? {
        var checkbox: Checkbox? = null
        zonesList?.let {
           with(it){
                horizontalLayout {
                    width = matchParent
                    marginTop = 4.px
                    alignItems = Alignment.Center
                    checkbox = checkbox {
                        margin = 8.px
                    }
                    textView {
                        text = label
                        textColor = DopaColors.main
                        width = matchParent
                        textAlign = TextView.TextAlign.Left
                        textSize = 18.px
                    }
                }
            }
        }
        return checkbox
    }
    fun LinearLayout.addLabeldCheckbox(label: String): Checkbox? {
        var checkbox: Checkbox? = null
                horizontalLayout {
                    width = matchParent
                    marginTop = 4.px
                    alignItems = Alignment.Center
                    checkbox = checkbox {
                        margin = 8.px
                    }
                    textView {
                        text = label
                        textColor = DopaColors.main
                        width = matchParent
                        textAlign = TextView.TextAlign.Left
                        textSize = 18.px
                    }
                }
        return checkbox
    }
}

fun LinearLayout.addTarget(name: String, onCheckboxChanged: (Event) -> Unit): Checkbox? {
    var checkbox: Checkbox? = null
    horizontalLayout {
        width = matchParent
        marginTop = 4.px
        padding = 4.px
        alignItems = Alignment.Center
        checkbox = checkbox {
            margin = 8.px
            onChange = onCheckboxChanged
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