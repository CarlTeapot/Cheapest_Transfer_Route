package org.example.zerobyte_assignment

import org.example.zerobyte_assignment.model.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import com.fasterxml.jackson.databind.ObjectMapper

@SpringBootTest
@AutoConfigureMockMvc
class TransferControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper
    private val address = "/transfer/process"
    @Test
    fun `test processRequest with valid data`() {
        val transfers = listOf(
            Transfer(weight = 10, cost = 60),
            Transfer(weight = 20, cost = 100),
            Transfer(weight = 30, cost = 120)
        )
        val request = TransferRequest(maxWeight = 50, availableTransfers = transfers)
        val requestJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(address)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalCost").value(220))
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalWeight").value(50))
            .andExpect(MockMvcResultMatchers.jsonPath("$.transfers").isArray)
    }

    @Test
    fun `test processRequest with empty transfers`() {

        val request = TransferRequest(maxWeight = 50, availableTransfers = emptyList())
        val requestJson = objectMapper.writeValueAsString(request)
        mockMvc.perform(
            MockMvcRequestBuilders.post(address)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalCost").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalWeight").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.transfers").isEmpty)
    }
}
