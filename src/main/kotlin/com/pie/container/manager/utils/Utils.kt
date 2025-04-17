package com.pie.container.manager.utils

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.dockerjava.transport.DockerHttpClient
import org.apache.http.entity.ContentType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import java.io.InputStream

fun setGetRequest(path: String): DockerHttpClient.Request {
    return DockerHttpClient.Request.builder().method(DockerHttpClient.Request.Method.GET).path("/$path").build()
}

fun <T> setPostRequest(path: String, body: T? = null): DockerHttpClient.Request {
    val requestBody: Any
    if (body is JsonNode) {
        requestBody = body
    } else if (body != null){
        requestBody = ObjectMapper().writeValueAsString(body)
    } else {
        requestBody = ObjectMapper().createObjectNode()
    }
    return DockerHttpClient.Request.builder()
        .method(DockerHttpClient.Request.Method.POST)
        .putHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
        .body(requestBody.toString().byteInputStream())
        .path("/$path").build()
}

private fun InputStream.toText() = (this.bufferedReader().use { it.readText() })

fun InputStream.toJson(): JsonNode = ObjectMapper().readTree(toText())

// TODO investigate or replace with standard Slf4 jogger. This doesn't properly capture the class name
inline val <reified T> T.logger: Logger
    get() = LoggerFactory.getLogger(T::class.java)
