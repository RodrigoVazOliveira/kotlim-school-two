package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.models.Course
import br.dev.rvz.forum.models.StatusTopic
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.Arrays

@RestController
@RequestMapping("/topics")
class TopicRestController {

    @GetMapping
    fun list(): List<Topic> {
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