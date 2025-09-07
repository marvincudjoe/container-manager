package com.pie.container.manager.service

import com.fasterxml.jackson.databind.JsonNode
import com.pie.container.manager.model.DefaultResponse

interface ImagesService {
    fun listImages(all: Boolean, filters: String, sharedSize: Boolean, digests: Boolean): DefaultResponse
    fun createImage(fromImage: String, fromSrc: String, repo: String, tag: String, payload: JsonNode): DefaultResponse
    fun removeAnImage(name: String, force: Boolean, noprune: Boolean): DefaultResponse
}