package org.example.zerobyte_assignment.model


// DTO for the response body of the transfer request
data class TransferResponse(
    val transfers: List<Transfer>,
    val totalCost: Int,
    val totalWeight: Int
)