package br.dev.rvz.forum.models

import java.time.LocalDateTime

object TopicTest {
    fun buildSave(author: User, course: Course) = Topic(
        id = null,
        title = "titulo de teste",
        message = "erro no meu codigo",
        responses = mutableListOf(),
        dateTimeUpdate = LocalDateTime.now(),
        dateTimeCreated = LocalDateTime.now(),
        author = author,
        course = course,
        status = StatusTopic.NO_RESPONSE
    )
}