package net.avatarapps.dopa.dashboard.dashboard.view.zones

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.District
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.Neighbourhood
import net.avatarapps.dopa.dashboard.dashboard.view.zones.areas.State
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.ImageView
import net.avatarapps.kunafa.core.components.TextInput
import net.avatarapps.kunafa.core.components.TextView
import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.components.layout.LinearLayout
import kotlin.js.Json

class ZonesPresenter : DashboardPlainPresenter() {

    var addZoneButton: TextView? = null
    var saveNewZoneButton: TextView? = null
    var cancelAddZoneButton: TextView? = null
    var noZonesTextView: TextView? = null
    var zonesListLoadingImageView: ImageView? = null
    var zonesList: LinearLayout? = null

    var username: TextInput? = null
    var name: TextInput? = null
    var password: TextInput? = null
    var phone: TextInput? = null
    var addZoneControlView: LinearLayout? = null
    var addZonesLoadingImageView: ImageView? = null
    var addZoneStatusText: TextView? = null
    private var zoneId: Int? = null
    private var isEditZone: Boolean = false

    val zonesListView = ZonesListView(this)
    private val addZoneView = AddZoneView(this)

    var states: MutableMap<State, AreaView> = mutableMapOf()
    var districts: MutableMap<District, AreaView> = mutableMapOf()
    var neighbourhoods: MutableMap<Neighbourhood, AreaView> = mutableMapOf()

    override fun onViewCreated(view: View) {
        getAndShowZones()
    }

    fun onAddZoneButtonClicked() {
        zoneId = null
        mainViewContent?.content = addZoneView
        saveNewZoneButton?.text = "Save new zone"
        password?.placeholder = "Password"
    }

    fun onSaveNewZoneButtonClicked() {

        if (validateField(name, "Name")) return
        val neighbourhoodIds = neighbourhoods.filter {
            it.value.checkbox?.isChecked == true
        }.map {
            it.key.id
        }

        ServerCaller.updateZone(
                ServerCaller.UpdateZoneRequestDto(
                        ServerCaller.UpdateZoneDto(
                                zoneId,
                                name?.text ?: "",
                                neighbourhoodIds
                        )),
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        getAndShowZones()

                    } else {
                        addZoneControlView?.isVisible = true
                        addZonesLoadingImageView?.isVisible = false

                    }
                },
                onError = {
                    addZoneControlView?.isVisible = true
                    addZonesLoadingImageView?.isVisible = false

                }
        )

    }

    private fun validateField(field: TextInput?, fieldName: String): Boolean {
        if (field?.text?.isNotEmpty() != true) {
            addZoneStatusText?.isVisible = true
            addZoneStatusText?.text = "$fieldName cannot be empty"
            return true
        } else {
            addZoneStatusText?.isVisible = false

        }
        return false
    }

    fun onCancelAddZoneButton() {
        getAndShowZones()

    }


    fun onEditZone(zone: ZoneDs) {
        mainViewContent?.content = addZoneView
        name?.text = zone.name
        zoneId = zone.id
        isEditZone = true
        saveNewZoneButton?.text = "Update zone"

        neighbourhoods.filter {
            it.key.id in zone.neighbourhoods
        }.forEach {
            it.value.checkbox?.isChecked = true
            // For districts
            val district: District? = updateCheckboxesForNeighbourhood(it.key)

            // For states
            updateCheckboxesForDistrict(district)
        }


    }

    private fun getAndShowZones() {
        mainViewContent?.content = zonesListView
        zonesList?.isVisible = false
        zonesListLoadingImageView?.isVisible = true

        ServerCaller.getAllZones(
                onSuccess = { xmlHttpRequest ->

                    if (xmlHttpRequest.status == 200.toShort()) {
                        zonesList?.isVisible = true
                        zonesListLoadingImageView?.isVisible = false

                        console.log(JSON.parse(xmlHttpRequest.responseText))
                        val zonesResponse = JSON.parse<Json>(xmlHttpRequest.responseText).get("data") as? Json
                        val zones = zonesResponse?.get("zones") as? Array<Json>

                        console.log(zones)
                        zones?.map {
                            val zonesIds = arrayListOf<Int>()
                            ZoneDs(it["id"] as? Int ?: 0,
                                    it["name"] as? String ?: "",
                                    (it["neighbourhoods"] as? Array<Int>)?.mapTo(zonesIds) { it } ?: arrayListOf()
                            )
                        }?.forEach {
                            zonesList?.addZone(it, this)
                        }
                    } else {

                        zonesList?.isVisible = false
                        zonesListLoadingImageView?.isVisible = false
                        noZonesTextView?.isVisible = true
                        noZonesTextView?.text = "Unknown error. Refresh page."
                    }
                },
                onError = {
                    zonesList?.isVisible = false
                    zonesListLoadingImageView?.isVisible = false
                    noZonesTextView?.isVisible = true
                    noZonesTextView?.text = "No internet connection. Refresh page."
                }
        )

    }

    fun onStateViewClicked(state: State) {
        val areaView = states[state]

        state.districts.forEach { district ->

            districts[district]?.checkbox?.isChecked = areaView?.checkbox?.isChecked ?: false

            district.neighbourhoods.forEach {
                neighbourhoods[it]?.checkbox?.isChecked = areaView?.checkbox?.isChecked ?: false
            }
        }

    }

    fun onDistrictViewClicked(district: District) {
        val areaView = districts[district]

        district.neighbourhoods.forEach {
            neighbourhoods[it]?.checkbox?.isChecked = areaView?.checkbox?.isChecked ?: false
        }

        // For states
        updateCheckboxesForDistrict(district)

    }

    fun onNeighbourhoodViewClicked(neighbourhood: Neighbourhood) {
        val areaView = neighbourhoods[neighbourhood]

        // For districts
        val district: District? = updateCheckboxesForNeighbourhood(neighbourhood)

        // For states
        updateCheckboxesForDistrict(district)

    }

    private fun updateCheckboxesForNeighbourhood(neighbourhood: Neighbourhood): District? {
        var district: District? = null

        districts.forEach { districtPair ->
            districtPair.key.neighbourhoods.forEach {
                if (it == neighbourhood) {
                    district = districtPair.key
                }
            }
        }

        val numberOfUnchecked = district?.
                neighbourhoods?.
                filter { neighbourhoods[it]?.checkbox?.isChecked != true }
                ?.size

        districts[district]?.checkbox?.isChecked = numberOfUnchecked == 0
        return district
    }

    private fun updateCheckboxesForDistrict(district: District?) {
        var state: State? = null

        states.forEach { statePair ->
            statePair.key.districts.forEach {
                if (it == district) {
                    state = statePair.key
                }
            }
        }

        val numberOfUncheckedDistricts = state?.
                districts?.
                filter { districts[it]?.checkbox?.isChecked != true }
                ?.size

        states[state]?.checkbox?.isChecked = numberOfUncheckedDistricts == 0
    }


}


class GetZonesResponseDto(
        val zones: ArrayList<ZoneInListDto>
)

class ZoneInListDto(
        val id: Int?,

        val name: String,

        val username: String,

        val phone: String,

        val zones: ArrayList<Int>,

        val approval: Boolean
)