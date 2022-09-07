package br.dev.rvz.forum.models.dto.topics

import br.dev.rvz.forum.models.StatusTopic
import java.io.Serializable
import java.time.LocalDateTime

data class TopicResponseDTO(
    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val dataCreated: LocalDateTime,
    val dateUpdate: LocalDateTime
) : Serializable {

}