package br.dev.rvz.forum.models.dto.topics

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class TopicUpdateRequestDTO(
    @field:NotNull
    val id: Long,

    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val message: String,

    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val title: String
)