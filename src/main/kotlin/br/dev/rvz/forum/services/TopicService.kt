package br.dev.rvz.forum.services

import br.dev.rvz.forum.models.Course
import br.dev.rvz.forum.models.StatusTopic
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.User
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicService(private var topics: List<Topic>) {

    init {
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

        val topicTwo = Topic(
            id = 2,
            title = "Duvida em Java",
            message = "Estou com problema em java",
            dateTimeCreated = LocalDateTime.now(),
            course = course,
            author = user,
            responses = listOf(),
            status = StatusTopic.NO_RESPONSE
        )

        val topicThree = Topic(
            id = 3,
            title = "Duvida em Java",
            message = "Estou com problema em java",
            dateTimeCreated = LocalDateTime.now(),
            course = course,
            author = user,
            responses = listOf(),
            status = StatusTopic.NO_RESPONSE
        )

        topics = arrayListOf(topic, topicTwo, topicThree)
    }

    fun list(): List<Topic> {
        return topics
    }

    fun getTopicById(id: Long): Topic {
        return topics.first { topic -> topic.id == id }
    }
}