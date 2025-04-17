package com.pie.container.manager.service

import com.pie.container.manager.controllers.CreateNetwork
import com.pie.container.manager.model.DefaultResponse

interface NetworksService {
    fun listNetworks(filters: String): DefaultResponse
    fun inspectANetwork(id: String, verbose: Boolean, scope: String): DefaultResponse
    fun createNetwork(body: CreateNetwork): DefaultResponse
}
