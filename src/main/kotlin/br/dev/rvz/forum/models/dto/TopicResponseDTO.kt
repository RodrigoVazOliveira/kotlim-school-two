package br.dev.rvz.forum.models.dto

import br.dev.rvz.forum.models.StatusTopic
import java.time.LocalDateTime

class TopicResponseDTO(
    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val dataCreated: LocalDateTime
)