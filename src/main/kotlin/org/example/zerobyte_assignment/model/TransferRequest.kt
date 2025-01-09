package org.example.zerobyte_assignment.model

data class TransferRequest(
    val maxWeight: Int,
    val availableTransfers: List<Transfer>
)