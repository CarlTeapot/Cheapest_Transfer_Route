package org.example.zerobyte_assignment.model

import org.springframework.stereotype.Component

data class TransferRequest(
    val maxWeight: Int,
    val availableTransfers: List<Transfer>
)