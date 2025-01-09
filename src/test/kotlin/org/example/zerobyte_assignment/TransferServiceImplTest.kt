package org.example.zerobyte_assignment

import org.example.zerobyte_assignment.model.Transfer
import org.example.zerobyte_assignment.model.TransferRequest
import org.example.zerobyte_assignment.service.TransferService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TransferServiceImplTest {
    @Autowired
    private lateinit var transferService: TransferService
    @Test
    fun `test processTransfer with valid data`() {
        val transfers = listOf(
            Transfer(weight = 10, cost = 60),
            Transfer(weight = 20, cost = 100),
            Transfer(weight = 30, cost = 120)
        )
        val request = TransferRequest(maxWeight = 50, availableTransfers = transfers)

        val result = transferService.processTransfer(request)

        assertNotNull(result)
        assertEquals(220, result.totalCost)
        assertEquals(50, result.totalWeight)
        assertEquals(2, result.transfers.size)
    }
    @Test
    fun `test processTransfer with no available transfers`() {
        val request = TransferRequest(maxWeight = 50, availableTransfers = emptyList())

        val result = transferService.processTransfer(request)

        assertNotNull(result)
        assertEquals(0, result.totalCost)
        assertEquals(0, result.totalWeight)
        assertTrue(result.transfers.isEmpty())
    }
}