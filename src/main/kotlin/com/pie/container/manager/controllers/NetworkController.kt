package com.pie.container.manager.controllers

import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.model.response
import com.pie.container.manager.service.NetworksService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @see [com.pie.container.manager.utils.DockerEngineApiReferences.Networks]
 */
@RestController
@RequestMapping("networks")
class NetworkController(private val networksService: NetworksService) {

    @GetMapping
    fun listNetworks(
        @Parameter(
            description = "JSON encoded value of the filters" + " (a `map[string][]string`) to process on the networks list."
        ) @RequestParam(required = false, defaultValue = "") filters: String
    ): ResponseEntity<DefaultResponse> = response { networksService.listNetworks(filters) }

    @GetMapping("/{id}")
    fun inspectANetwork(
        @Parameter(description = "Network ID or name") @PathVariable id: String,
        @Parameter(description = "Detailed inspect output for troubleshooting") @RequestParam(
            required = false,
            defaultValue = "false"
        ) verbose: Boolean,
        @Parameter(description = "Filter the network by scope (swarm, global, or local)") @RequestParam(
            required = false,
            defaultValue = ""
        ) scope: String
    ): ResponseEntity<DefaultResponse> = response { networksService.inspectANetwork(id, verbose, scope) }

    @PostMapping
    fun createNetwork(@RequestBody body: CreateNetwork
    ): ResponseEntity<DefaultResponse> = response { networksService.createNetwork(body) }
}
