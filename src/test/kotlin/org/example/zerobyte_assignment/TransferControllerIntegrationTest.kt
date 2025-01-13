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
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
    @Test
    fun `test processRandomRequest returns valid data`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/transfer/processRandom")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.request").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.response").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.request.maxWeight").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("$.request.availableTransfers").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("$.response.totalCost").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("$.response.totalWeight").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("$.response.transfers").isArray)
    }

}
