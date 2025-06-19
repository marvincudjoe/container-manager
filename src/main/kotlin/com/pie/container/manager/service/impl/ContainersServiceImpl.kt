package com.pie.container.manager.service.impl

import com.fasterxml.jackson.databind.JsonNode
import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.service.ContainersService
import com.pie.container.manager.utils.DockerEngineApiReferences
import com.pie.container.manager.utils.setGetRequest
import com.pie.container.manager.utils.setPostRequest
import org.springframework.stereotype.Service

/**
 * @see ContainersService
 */
private const val PREFIX: String = "containers"

@Service
class ContainersServiceImpl(val daemonService: DaemonServiceImpl) : ContainersService {

    override fun listContainers(all: Boolean, limit: Int, size: Boolean, filters: String): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("$PREFIX/json?all=$all&limit=$limit&size=$size&filters=$filters"),
            DockerEngineApiReferences.Containers.LIST
        )
    }

    override fun createContainer(name: String, platform: String, payload: JsonNode): DefaultResponse {
        return daemonService.sendRequest(
            setPostRequest("$PREFIX/create?name=$name&platform=$platform", payload),
            DockerEngineApiReferences.Containers.CREATE
        )
    }

    override fun inspectContainer(id: String, size: Boolean): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("$PREFIX/$id/json?size=$size"), DockerEngineApiReferences.Containers.INSPECT
        )
    }

    override fun startContainer(id: String, detachKeys: String): DefaultResponse {
        return if (detachKeys.isEmpty()) {
            daemonService.sendRequest(
                setPostRequest("$PREFIX/$id/start", null), DockerEngineApiReferences.Containers.START
            )
        } else {
            daemonService.sendRequest(
                setPostRequest("$PREFIX/$id/start?detachKeys=$detachKeys", null),
                DockerEngineApiReferences.Containers.START
            )
        }
    }

    override fun stopContainer(id: String, signal: String, t: Int): DefaultResponse {
        return daemonService.sendRequest(
            setPostRequest("$PREFIX/$id/stop?signal=$signal&t=$t", null), DockerEngineApiReferences.Containers.STOP
        )
    }

    override fun restartContainer(id: String, signal: String, t: Int): DefaultResponse {
        return daemonService.sendRequest(
            setPostRequest("$PREFIX/$id/restart?signal=$signal&t=$t", null), DockerEngineApiReferences.Containers.RESTART
        )
    }

    override fun deleteStoppedContainers(filters: String): DefaultResponse {
        return daemonService.sendRequest(
            setPostRequest("$PREFIX/prune$filters", null), DockerEngineApiReferences.Containers.CONTAINER_PRUNE
        )
    }
}
