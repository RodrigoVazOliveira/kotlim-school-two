package br.dev.rvz.forum.models

import java.time.LocalDateTime

data class Topic(
    val id: Long? = null,
    val title: String,
    val message: String,
    val dateTimeCreated: LocalDateTime,
    val course: Course,
    val author: User,
    val status: StatusTopic,
    val responses: MutableList<ResponseTopic>
)