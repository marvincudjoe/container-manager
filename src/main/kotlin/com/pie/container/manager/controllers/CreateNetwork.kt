package com.pie.container.manager.controllers

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateNetwork @JsonCreator constructor(@JsonProperty var name: String)