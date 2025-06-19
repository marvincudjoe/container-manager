package com.pie.container.manager.service.impl

import com.fasterxml.jackson.databind.JsonNode
import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.model.endpointNotImplemented
import com.pie.container.manager.service.ImagesService
import com.pie.container.manager.utils.DockerEngineApiReferences
import com.pie.container.manager.utils.setGetRequest
import org.springframework.stereotype.Service

/**
 * @see ImagesService
 */
private const val PREFIX: String = "images"

@Service
class ImagesServiceImpl(val daemonService: DaemonServiceImpl) : ImagesService {

    override fun listImages(all: Boolean, filters: String, sharedSize: Boolean, digests: Boolean): DefaultResponse =
        daemonService.sendRequest(
            setGetRequest("$PREFIX/json?all=$all&filters=$filters&shared-size=$sharedSize&digests=$digests"),
            DockerEngineApiReferences.Images.LIST
        )

    override fun createImage(payload: JsonNode): DefaultResponse =
        endpointNotImplemented(DockerEngineApiReferences.Images.CREATE)

    override fun removeAnImage(name: String, force: Boolean, noprune: Boolean): DefaultResponse =
        daemonService.sendRequest(
            setGetRequest("$PREFIX/$name?force=$force&noprune=$noprune"),
            DockerEngineApiReferences.Images.DELETE
        )
}
