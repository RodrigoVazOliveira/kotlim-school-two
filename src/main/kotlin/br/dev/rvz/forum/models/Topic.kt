package br.dev.rvz.forum.models

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class Topic(
    val id: Long? = null,
    val title: String,
    val message: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val dateTimeCreated: LocalDateTime,
    val course: Course,
    val author: User,
    val status: StatusTopic,
    val responses: List<ResponseTopic>
)