package net.avatarapps.dopa.dashboard.dashboard.view.salesmen

data class SalesmanDs(
        val name: String,
        val username: String,
        val password: String,
        val zoneId: ArrayList<Int>,
        val phone: String,
        val agentApproval: Boolean,
        var id: Int? = null
)