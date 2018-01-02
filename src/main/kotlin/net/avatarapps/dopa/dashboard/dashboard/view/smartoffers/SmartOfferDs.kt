package net.avatarapps.dopa.dashboard.dashboard.view.smartoffers

data class SmartOfferDs(
        val name: String,
        val username: String,
        val password: String,
        val zoneId: ArrayList<Int>,
        val phone: String,
        var id: Int? = null
)