package br.dev.rvz.forum.models.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class AuthorSavedDTO(

    @field:NotEmpty(message = "O campo nome noa pode ser vazio")
    val name: String,

    @field:Email(message = "O email informado nao e valido")
    @field:NotEmpty(message = "O email e obrigatorio")
    val email: String,

    @field:NotEmpty(message = "a senha e obrigatorio")
    val password: String,

    @field:NotNull
    val roles: List<String>
)