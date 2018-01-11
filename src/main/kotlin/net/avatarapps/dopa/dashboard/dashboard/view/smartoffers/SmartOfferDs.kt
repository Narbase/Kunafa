package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers


data class SmartOfferDs(
        val id: Int,
        val offerDescription: String,
        val drugId: Int,
        val drugName: String,
        val targetIsAll: Boolean,
        val targetContainsZones: Boolean,
        val targetContainsLabels: Boolean,
        val targetContainsPharmaciesWithPreviousInteractions: Boolean,
        val targetedLabels: String?,
        var targetedZonesNames: Array<String>
)