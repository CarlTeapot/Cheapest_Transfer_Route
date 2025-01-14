package org.example.zerobyte_assignment.model

//DTO for the response body of the random transfer request
data class FullTransferResponse(
    val request: TransferRequest,
    val response: TransferResponse
)