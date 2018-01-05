package net.avatarapps.dopa.dashboard.dashboard.view.zones

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.Areas
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.District
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.Neighbourhood
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.State
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


class ZonesView : DashboardPlainViewContent("Zones management") {


    override val plainPresenter = ZonesPresenter()

    override var pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            textView {
                text = "Zones management"
            }
        }
    }
}

class ZonesListView(private val zonesPresenter: ZonesPresenter) : ViewContent() {
    override fun DetachedView.contentDefinition() {

        verticalLayout {
            width = matchParent
            height = wrapContent

            zonesPresenter.addZoneButton = textView {
                width = wrapContent
                height = wrapContent
                padding = 8.px
                background = DopaColors.main
                text = "+ Add zone"
                makeClickable()
                textColor = Color.white
                width = wrapContent
                onClick = { zonesPresenter.onAddZoneButtonClicked() }
            }

            zonesPresenter.noZonesTextView = textView {
                text = "No zones were added yet."
                textColor = DopaColors.separatorLight
                textSize = 24.px
                width = matchParent
                textAlign = TextView.TextAlign.Center
                marginTop = 16.px
                isVisible = false
            }

            zonesPresenter.zonesListLoadingImageView = loadingIndicator()

            zonesPresenter.zonesList = verticalLayout {
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


fun LinearLayout.addZone(zone: ZoneDs, zonesPresenter: ZonesPresenter) {
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
                    text = zone.name
                    textColor = DopaColors.main
                    textSize = 18.px
                }
            }

            textView {
                text = "Edit"
                makeClickable()
                background = DopaColors.mainLight
                textColor = Color.white
                padding = 8.px
                alignSelf = Alignment.Center
                onClick = { zonesPresenter.onEditZone(zone) }
            }
        }

        view {
            width = matchParent
            height = 2.px
            background = DopaColors.separatorLight
        }
    }
}

class AddZoneView(private val zonesPresenter: ZonesPresenter) : ViewContent() {
    override fun DetachedView.contentDefinition() {
        val formWidth = 640.px

        verticalLayout {
            width = matchParent
            alignItems = Alignment.Center
            height = wrapContent

            verticalLayout {
                width = matchParent
                maxWidth = formWidth
                alignItems = Alignment.Center
                height = wrapContent

                zonesPresenter.addZoneControlView = horizontalLayout {
                    width = matchParent
                    maxWidth = formWidth

                    zonesPresenter.cancelAddZoneButton = textView {
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
                        onClick = { zonesPresenter.onCancelAddZoneButton() }
                    }

                    zonesPresenter.saveNewZoneButton = textView {
                        padding = 8.px
                        width = weightOf(1)
                        height = wrapContent
                        textColor = Color.white
                        text = "Save new zone"
                        makeClickable(DopaColors.greenLight)
                        background = DopaColors.greenLight
                        width = wrapContent
                        textAlign = TextView.TextAlign.Center
                        onClick = { zonesPresenter.onSaveNewZoneButtonClicked() }
                    }

                }
                zonesPresenter.addZonesLoadingImageView = loadingIndicator()

                zonesPresenter.addZoneStatusText = textView {
                    text = ""
                    textColor = DopaColors.redLight
                    textSize = 14.px
                    width = matchParent
                    textAlign = TextView.TextAlign.Center
                    marginTop = 8.px
                    isVisible = false
                }


                zonesPresenter.name = textInput {
                    placeholder = "Zone name"
                    width = matchParent
                    textSize = 18.px
                    marginBottom = 12.px
                    marginTop = 18.px
                    maxWidth = formWidth
                }

                Areas.states.forEach { state ->
                    zonesPresenter.states.put(state, addState(state))

                    state.districts.forEach { district ->
                        zonesPresenter.districts.put(district, addDistrict(district))

                        district.neighbourhoods.forEach { neighbourhood ->
                            zonesPresenter.neighbourhoods.put(neighbourhood, addNeighbourhood(neighbourhood))
                        }
                    }
                }

            }
        }
    }

    private fun LinearLayout.addState(state: State): AreaView {
        var checkbox: Checkbox? = null
        val body = verticalLayout {
            width = matchParent
            height = wrapContent
            horizontalLayout {
                width = matchParent
                height = wrapContent
                background = Color.white
                padding = 8.px
                alignItems = Alignment.Center

                checkbox = checkbox {
                    margin = 8.px
                    onChange = { zonesPresenter.onStateViewClicked(state) }
                }
                textView {
                    text = state.nameAr

                }
            }
            view {
                width = matchParent
                height = 1.px
                background = DopaColors.separatorLight
            }
        }
        return AreaView(body, checkbox)
    }


    private fun LinearLayout.addDistrict(district: District): AreaView {
        var checkbox: Checkbox? = null

        val body = horizontalLayout {
            width = matchParent
            height = wrapContent
            background = Color.white
            padding = 8.px
            alignItems = Alignment.Center
            paddingStart = 40.px

            checkbox = checkbox {
                margin = 8.px
                onChange = { zonesPresenter.onDistrictViewClicked(district) }
            }
            textView {
                text = district.nameAr

            }

        }
        return AreaView(body, checkbox)
    }


    private fun LinearLayout.addNeighbourhood(neighbourhood: Neighbourhood): AreaView {
        var checkbox: Checkbox? = null

        val body = horizontalLayout {
            width = matchParent
            height = wrapContent
            background = Color.white
            padding = 8.px
            alignItems = Alignment.Center
            paddingStart = 80.px

            checkbox = checkbox {
                margin = 8.px
                onChange = { zonesPresenter.onNeighbourhoodViewClicked(neighbourhood) }
            }
            textView {
                text = neighbourhood.nameAr

            }

        }
        return AreaView(body, checkbox)
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

class AreaView(
        val body: LinearLayout,
        val checkbox: Checkbox?
)