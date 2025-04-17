package com.pie.container.manager.utils

private const val DOCKER_ENGINE_VERSION = "latest" // version/v1.49
private const val DOCKER_API_REF =
    "https://docs.docker.com/reference/api/engine/$DOCKER_ENGINE_VERSION/#tag/%s/operation/%s"

class DockerEngineApiReferences {
    /**
     * Create and manage containers
     */
    object Containers {
        val LIST = DOCKER_API_REF.format("Container", "ContainerList")
        val CREATE = DOCKER_API_REF.format("Container", "ContainerCreate")
        val INSPECT = DOCKER_API_REF.format("Container", "ContainerInspect")
        val START = DOCKER_API_REF.format("Container", "ContainerStart")
        val STOP = DOCKER_API_REF.format("Container", "ContainerStop")
        val RESTART = DOCKER_API_REF.format("Container", "ContainerRestart")
        val CONTAINER_PRUNE = DOCKER_API_REF.format("Container", "ContainerPrune")
    }

    /**
     * Create and manage images
     */
    object Images {
        val LIST = DOCKER_API_REF.format("Image", "ImageList")
        val CREATE = DOCKER_API_REF.format("Image", "ImageCreate")
        val DELETE = DOCKER_API_REF.format("Image", "ImageDelete")
    }

    /**
     * Create and manage networks
     */
    object Networks {
        val LIST = DOCKER_API_REF.format("Network", "NetworkList")
        val CREATE = DOCKER_API_REF.format("Network", "NetworkCreate")
        val INSPECT = DOCKER_API_REF.format("Network", "NetworkInspect")
    }

    /**
     * Create and manage volumes
     */
    object Volumes {
        val LIST = DOCKER_API_REF.format("Volume", "VolumeList")
        val CREATE = DOCKER_API_REF.format("Volume", "VolumeCreate")
        val INSPECT = DOCKER_API_REF.format("Volume", "VolumeInspect")
        val DELETE = DOCKER_API_REF.format("Volume", "VolumeDelete")
        val PRUNE = DOCKER_API_REF.format("Volume", "VolumePrune")
    }

    /**
     *
     */
    object System {
        val PING_HEAD = DOCKER_API_REF.format("System", "SystemPingHead")
        val VERSION = DOCKER_API_REF.format("System", "SystemVersion")
    }
}
