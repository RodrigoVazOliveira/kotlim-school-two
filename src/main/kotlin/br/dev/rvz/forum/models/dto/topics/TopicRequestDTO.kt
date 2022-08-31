package br.dev.rvz.forum.models.dto.topics

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class TopicRequestDTO(
    @field:NotEmpty(message = "O campo titulo esta vazio")
    @field:Size(min = 5, max = 100, message = "O texto deve ser maior que 5 e no maximo 100 caracteres")
    val title: String,
    @field:NotEmpty
    val message: String,

    @field:NotNull
    val idCourse: Long,

    @field:NotNull
    val idAuthor: Long
)