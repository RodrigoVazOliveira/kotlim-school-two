package br.dev.rvz.forum.models.dto.responses

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class ResponseTopicRequestDTO(
    @field:NotEmpty
    val message: String,
    @field:NotNull
    val idAuthor: Long,
    var idTopic: Long
)
