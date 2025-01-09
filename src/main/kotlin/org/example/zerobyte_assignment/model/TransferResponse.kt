package org.example.zerobyte_assignment.model

data class TransferResponse(
    val transfers: List<Transfer>,
    val totalCost: Int,
    val totalWeight: Int
)