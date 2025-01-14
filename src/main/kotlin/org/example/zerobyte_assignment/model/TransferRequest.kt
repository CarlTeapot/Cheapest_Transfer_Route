package org.example.zerobyte_assignment.model

//DTO for the request body of the transfer request
data class TransferRequest(
    val maxWeight: Int,
    val availableTransfers: List<Transfer>
)