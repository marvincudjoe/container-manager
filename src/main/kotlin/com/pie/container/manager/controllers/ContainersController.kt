package com.pie.container.manager.controllers

import com.fasterxml.jackson.databind.JsonNode
import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.model.response
import com.pie.container.manager.service.ContainersService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @see [com.pie.container.manager.utils.DockerEngineApiReferences.Containers]
 */
@RestController
@RequestMapping("containers")
class ContainersController(private val containersService: ContainersService) {

    @GetMapping("json")
    fun listContainers(
        @Parameter(description = "Return all containers.")
        @RequestParam(required = false, defaultValue = "false") all: Boolean,
        @Parameter(description = "Return this number of most recently created containers, including non-running ones.")
        @RequestParam(required = false, defaultValue = "0") limit: Int,
        @Parameter(description = "Return the size of container as fields SizeRw and SizeRootFs.")
        @RequestParam(required = false, defaultValue = "false") size: Boolean,
        @Parameter(description = "Filters to process on the container list, encoded as JSON (a `map[string][]string`).")
        @RequestParam(required = false, defaultValue = "") filters: String,
    ): ResponseEntity<DefaultResponse> =
        response { containersService.listContainers(all, limit, size, filters) }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createContainer(
        @Parameter(description = "Assign the specified name to the container. Must match `/?[a-zA-Z0-9][a-zA-Z0-9_.-]+`.")
        @RequestParam(required = false, defaultValue = "") name: String,
        //       TODO Create custom validator for @Pattern(regexp = "^/?[a-zA-Z0-9][a-zA-Z0-9_.-]+\$")
        @Parameter(description = "Platform in the format `os[/arch[/variant]]` used for image lookup.")
        @RequestParam(required = false, defaultValue = "") platform: String,
        @RequestBody jsonPayload: JsonNode
    ): ResponseEntity<DefaultResponse> =
        response { containersService.createContainer(name, platform, jsonPayload) }

    @GetMapping("{id}/json")
    fun inspectContainer(
        @Parameter(description = "Name or Hash of the container to inspect.")
        @PathVariable id: String,
        @Parameter(description = "Return the size of container as fields SizeRw and SizeRootFs.")
        @RequestParam(required = false, defaultValue = "false") size: Boolean
    ): ResponseEntity<DefaultResponse> = response { containersService.inspectContainer(id, size) }

    /**
     * See [Attach](https://docs.docker.com/engine/reference/commandline/attach/#detach-keys)
     */
    @PostMapping("{id}/start")
    fun startContainer(
        @Parameter(description = "Name or Hash of the container to start.")
        @PathVariable id: String,
        @Parameter(
            description = "Override the key sequence for detaching a container. ",
            hidden = true
        )
        @RequestParam(required = false, defaultValue = "") detachKeys: String
    ): ResponseEntity<DefaultResponse> = response { containersService.startContainer(id, detachKeys) }

    @PostMapping("{id}/stop")
    fun stopContainer(
        @Parameter(description = "Name or Hash of the container to stop.")
        @PathVariable id: String,
        @Parameter(description = "Signal to send to the container as an integer or string.")
        @RequestParam(required = false, defaultValue = "SIGTERM") signal: String,
        @Parameter(description = "Number of seconds to wait before killing the container.")
        @RequestParam(required = false, defaultValue = "0") t: Int
    ): ResponseEntity<DefaultResponse> = response { containersService.stopContainer(id, signal, t) }

    @PostMapping("{id}/restart")
    fun restartContainer(
        @Parameter(description = "Name or Hash of the container to stop.")
        @PathVariable id: String,
        @Parameter(description = "Signal to send to the container as an integer or string.")
        @RequestParam(required = false, defaultValue = "SIGTERM") signal: String,
        @Parameter(description = "Number of seconds to wait before killing the container.")
        @RequestParam(required = false, defaultValue = "0") t: Int
    ): ResponseEntity<DefaultResponse> =
        response { containersService.restartContainer(id, signal, t) }

    @PostMapping("prune")
    fun deleteStoppedContainers(
        @Parameter(description = "Filters to process on the prune list, encoded as JSON (a `map[string][]string`).")
        @RequestParam(required = false, defaultValue = "") filters: String
    ): ResponseEntity<DefaultResponse> =
        response { containersService.deleteStoppedContainers(filters) }
}
