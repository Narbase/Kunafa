package net.avatarapps.dopa.dashboard.dashboard.view.salesmenrequests

import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.kunafa.core.components.View
import kotlin.js.Json

class SalesmenRequestsPresenter : DashboardPlainPresenter() {
    override fun onViewCreated(view: View) {
    }
}

data class ResponseDto(
        val data: GetRequestsResponseDto
)

data class GetRequestsResponseDto(
        val pendingRequests: Array<PendingRequest>
)

data class PendingRequest(
        val request: PendingRequestDto,
        val salesmanDto: PendingRequestSalesmanDto,
        val pharmacyDs: PendingRequestPharmacyDto
)

data class PendingRequestDto(
        var id: Int? = null,
        val deliveryDate: String,
        val orderItemsList: List<PendingRequestItemsDto>,
        val payments: List<PendingRequestPaymentDto>
)

data class PendingRequestItemsDto(
        val amount: Int,
        val price: Double,
        val bonus: Int,
        val discount: Int,
        val productId: Int,
        val productName: String,
        val expirationDate: String
)

data class PendingRequestPaymentDto(
        val date: String,
        val price: Double
)

data class PendingRequestSalesmanDto(
        val name: String,
        val phone: String
)

data class PendingRequestPharmacyDto(
        val nameEnglish: String,
        val nameArabic: String,
        val locationDescription: String,
        val firstPhone: String,
        val secondPhone: String,
        val pharmacistName: String,
        var lat: Double,
        var lng: Double,
        var neighborhoodId: Int
)
