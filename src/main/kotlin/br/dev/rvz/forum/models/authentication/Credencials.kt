package br.dev.rvz.forum.models.authentication

import com.fasterxml.jackson.annotation.JsonProperty

data class Credencials(
    @JsonProperty("username")
    val username: String,

    @JsonProperty("password")
    val password: String
)