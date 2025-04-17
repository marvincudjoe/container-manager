package com.pie.container.manager.service

import com.pie.container.manager.model.DefaultResponse

interface SystemService {
    fun pingDaemon(): DefaultResponse
    fun version(): DefaultResponse
}
