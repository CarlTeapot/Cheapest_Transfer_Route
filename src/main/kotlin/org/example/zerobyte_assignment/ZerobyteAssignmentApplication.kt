package org.example.zerobyte_assignment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])

class ZerobyteAssignmentApplication
fun main(args: Array<String>) {
    runApplication<ZerobyteAssignmentApplication>(*args)
}
