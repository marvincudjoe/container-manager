package com.pie.container.manager.service.impl

import com.pie.container.manager.controllers.CreateNetwork
import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.service.NetworksService
import com.pie.container.manager.utils.DockerEngineApiReferences
import com.pie.container.manager.utils.setGetRequest
import com.pie.container.manager.utils.setPostRequest
import org.springframework.stereotype.Service

@Service
class NetworksServiceImpl : NetworksService {

    private var daemonService = DaemonServiceImpl()

    override fun listNetworks(filters: String): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("networks?filters=$filters"), DockerEngineApiReferences.Networks.LIST
        )
    }

    override fun inspectANetwork(id: String, verbose: Boolean, scope: String): DefaultResponse {
        return daemonService.sendRequest(
            setGetRequest("networks/$id?verbose=$verbose&scope$scope"), DockerEngineApiReferences.Networks.INSPECT
        )
    }

    override fun createNetwork(body: CreateNetwork): DefaultResponse {
        return daemonService.sendRequest(
            setPostRequest("networks/create", body), DockerEngineApiReferences.Networks.CREATE
        )
    }
}
