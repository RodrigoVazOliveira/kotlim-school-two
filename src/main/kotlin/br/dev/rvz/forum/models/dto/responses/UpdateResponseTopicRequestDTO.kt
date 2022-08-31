package br.dev.rvz.forum.models.dto.responses

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class UpdateResponseTopicRequestDTO(
    @field:NotNull
    val id: Long,

    @field:NotEmpty
    val message: String
)
