# Container Manager

Spring boot web application to interact with the Docker daemon, [Docker Engine API](https://docs.docker.com/engine/api/).
Built on top of [docker-java](https://github.com/docker-java/docker-java).

## Pre-requisites
- [JDK 21](https://adoptium.net/en-GB/temurin/releases/)
- [Docker](https://docs.docker.com/get-docker/)

## Running the application

- Docker Daemon must be up and running.
- The Docker socket must be reachable. See [Permission requirements](https://docs.docker.com/desktop/setup/install/mac-permission-requirements/#permission-requirements).

This project uses [Gradle](https://gradle.org/) as the build tool.

On Linux:
```shell
./gradlew bootRun
```

On Windows:
```shell
gradlew.bat bootRun
```

<details>
    <summary>Alternative: Docker</summary>

```shell
docker compose up
```

To clean up
```shell
docker compose down
```

</details>

### Docs

See [API collection](docs/collection).

### Swagger

- UI: http://localhost:8080/swagger-ui/index.html
- JSON: http://localhost:8080/v3/api-docs

### Available Actuator Endpoints

- http://localhost:8080/actuator
- http://localhost:8080/actuator/health
- http://localhost:8080/actuator/metrics


## NOTES
Better solutions exist like [Testcontainers](https://testcontainers.com/) and [ContainerSSH](https://containerssh.io/).

This is a work in progress. I *may* add updates as I progress in studying Kotlin and other development practices.
