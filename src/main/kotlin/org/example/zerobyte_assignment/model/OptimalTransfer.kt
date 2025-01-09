package org.example.zerobyte_assignment.model

import org.example.zerobyte_assignment.model.*

data class OptimalTransfer(
    val transfers: List<Transfer>,
    val totalCost: Int,
    val totalWeight: Int
)