package br.dev.rvz.forum.models

import java.time.LocalDateTime

data class ResponseTopic(
    val id: Long?,
    val message: String,
    val dateTimeCreated: LocalDateTime,
    val author: User,
    val topic: Topic,
    val soluction: Boolean
)
