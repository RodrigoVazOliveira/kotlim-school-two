package br.dev.rvz.forum.services

import br.dev.rvz.forum.models.Course
import br.dev.rvz.forum.models.StatusTopic
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.User
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class TopicService {

    fun list() : List<Topic> {
        val user = User(id = 1, name = "Rodrigo Vaz", email = "rodrigo@gmail.com")
        val course = Course(id = 1, name = "Spring Boot", "Programacao")
        val topic = Topic(
            id = 1,
            title = "Duvida em Java",
            message = "Estou com problema em java",
            dateTimeCreated = LocalDateTime.now(),
            course = course,
            author = user,
            responses = listOf(),
            status = StatusTopic.NO_RESPONSE
        )

        return Arrays.asList(topic, topic, topic)
    }
}