package com.pie.container.manager.controllers

import com.fasterxml.jackson.databind.JsonNode
import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.model.response
import com.pie.container.manager.service.ImagesService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @see [com.pie.container.manager.utils.DockerEngineApiReferences.Images]
 */
@RestController
@RequestMapping("images")
class ImagesController(private val imagesService: ImagesService) {

    @GetMapping("json")
    fun listImages(
        @Parameter(description = "Show all images. Only images from a final layer (no children) are shown by default.")
        @RequestParam(required = false, defaultValue = "false") all: Boolean,
        @Parameter(description = "A JSON encoded value of the filters (a `map[string][]string`) to process on the images list.")
        @RequestParam(required = false, defaultValue = "") filters: String,
        @Parameter(description = "Compute and show shared size as a `SharedSize` field on each image.")
        @RequestParam(required = false, defaultValue = "false") sharedSize: Boolean,
        @Parameter(description = "Show digest information as a `RepoDigests` field on each image")
        @RequestParam(required = false, defaultValue = "false") digests: Boolean
        // TODO: filters query param is not implemented/Doesn't work as expected
    ): ResponseEntity<DefaultResponse> = response { imagesService.listImages(all, filters, sharedSize, digests) }

    @PostMapping
    fun createImage(@RequestBody payload: JsonNode): ResponseEntity<DefaultResponse> =
        response { imagesService.createImage(payload) }

    @DeleteMapping("{name}")
    fun removeAnImage(
        @Parameter(description = "Image name or ID") @PathVariable name: String,
        @Parameter(description = "Remove the image even if it is being used by stopped containers or has other tags")
        @RequestParam(required = false, defaultValue = "false") force: Boolean,
        @Parameter(description = "Do not delete untagged parent images")
        @RequestParam(required = false, defaultValue = "false") noprune: Boolean
    ): ResponseEntity<DefaultResponse> = response { imagesService.removeAnImage(name, force, noprune) }
}
