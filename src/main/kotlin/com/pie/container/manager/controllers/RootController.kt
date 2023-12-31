package com.pie.container.manager.controllers

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Hidden
@RestController
@RequestMapping("/")
class RootController {
    @GetMapping
    fun welcome(): String = "Welcome to container-manager"
}
