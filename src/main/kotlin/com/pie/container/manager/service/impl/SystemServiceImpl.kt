package com.pie.container.manager.service.impl

import com.pie.container.manager.model.DefaultResponse
import com.pie.container.manager.service.SystemService
import com.pie.container.manager.utils.DockerEngineApiReferences
import com.pie.container.manager.utils.setGetRequest
import org.springframework.stereotype.Service

/**
 * @see SystemService
 */
@Service
class SystemServiceImpl : SystemService {

    private var daemonService = DaemonServiceImpl()

    override fun pingDaemon(): DefaultResponse =
        daemonService.sendRequest(setGetRequest("_ping"), DockerEngineApiReferences.System.PING_HEAD)

    override fun version(): DefaultResponse =
        daemonService.sendRequest(setGetRequest("version"), DockerEngineApiReferences.System.VERSION)
}
