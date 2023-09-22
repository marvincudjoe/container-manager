package com.pie.container.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application
fun main(args: Array<String>) {
    runApplication<Application>(*args) {
        //setLogStartupInfo(false) todo: uncomment this line in the future
    }
}
