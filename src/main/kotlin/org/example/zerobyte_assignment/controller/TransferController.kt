package org.example.zerobyte_assignment.controller

import org.example.zerobyte_assignment.model.*
import org.example.zerobyte_assignment.service.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("transfer")
class TransferController(private val transferService : TransferService) {

    @PostMapping("/process")
    fun processRequest(@RequestBody requestBody: TransferRequest): ResponseEntity<OptimalTransfer> {
        val response = transferService.processTransfer(requestBody);
        return ResponseEntity(response, HttpStatus.OK)
    }
}

