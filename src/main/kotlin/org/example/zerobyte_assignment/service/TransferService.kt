package org.example.zerobyte_assignment.service

import org.example.zerobyte_assignment.model.*
import org.springframework.stereotype.Service

@Service
interface TransferService {
    fun processTransfer(requestBody: TransferRequest): TransferResponse
    fun processRandomTransfer() : FullTransferResponse
}