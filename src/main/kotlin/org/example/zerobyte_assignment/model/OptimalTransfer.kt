package org.example.zerobyte_assignment.model

import lombok.AllArgsConstructor
import org.example.zerobyte_assignment.model.*
import org.springframework.stereotype.Component

data class OptimalTransfer(
    val transfers: List<Transfer>,
    val totalCost: Int,
    val totalWeight: Int
)