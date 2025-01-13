package org.example.zerobyte_assignment.service.Implementation

import org.example.zerobyte_assignment.model.*
import org.example.zerobyte_assignment.service.TransferService
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class TransferServiceImpl : TransferService {
    //the problem in the statement is equivalent to a knapsack problem
    // which is a very well known problem in computer science
    // most optimal solution involves dynamic programming
    // the time complexity of the solution is O(N*maxWeight)
    private fun solveKnapsack(requestBody: TransferRequest) : TransferResponse {
        val maxWeight:Int = requestBody.maxWeight
        val N:Int = requestBody.availableTransfers.size
        val dp = Array(N + 1) { IntArray(maxWeight + 1) }
        for (i in 0..N) {
            for (j in 0..maxWeight) {
                if (i == 0 || j == 0 ) {
                    dp[i][j] = 0
                }
                else if (requestBody.availableTransfers[i - 1].weight <= j) {
                    dp[i][j] = maxOf(requestBody.availableTransfers[i - 1].cost + dp[i - 1][j - requestBody.availableTransfers[i - 1].weight], dp[i - 1][j])
                }
                else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }
        var remWeight = maxWeight
        var rem = dp[N][maxWeight]
        val optimalTransfers = mutableListOf<Transfer>()
        var usedWeight = 0
        for (i in N downTo 1) {
            if (rem <= 0) {
                break
            }
            if (rem == dp[i - 1][remWeight]) {
                continue
            }
            optimalTransfers.add(requestBody.availableTransfers[i - 1])
            rem -= requestBody.availableTransfers[i - 1].cost
            remWeight -= requestBody.availableTransfers[i - 1].weight
            usedWeight += requestBody.availableTransfers[i - 1].weight
        }
        return TransferResponse(optimalTransfers, dp[N][maxWeight], usedWeight)
    }

    //process
    override fun processTransfer(requestBody: TransferRequest): TransferResponse {
        return solveKnapsack(requestBody)
    }
    override fun processRandomTransfer() : FullTransferResponse {
        val maxWeight = Random.nextInt(0,5000)
        val n = Random.nextInt(0,1500)
        val transfers = mutableListOf<Transfer>()
        for (i in 0..n) {
            transfers.add(Transfer(Random.nextInt(0,5000), Random.nextInt(0,1000)))
        }
        val request = TransferRequest(maxWeight, transfers)
        val response = processTransfer(request)
        return FullTransferResponse(request, response)
    }
}