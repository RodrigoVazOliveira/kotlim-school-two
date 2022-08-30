package br.dev.rvz.forum.services

import br.dev.rvz.forum.mappers.topics.TopicRequestMapper
import br.dev.rvz.forum.mappers.topics.TopicResponseListMapper
import br.dev.rvz.forum.models.*
import br.dev.rvz.forum.models.dto.topics.TopicRequestDTO
import br.dev.rvz.forum.models.dto.topics.TopicResponseDTO
import br.dev.rvz.forum.models.dto.topics.TopicUpdateRequestDTO
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicService(
    private var topics: List<Topic>,
    private val topicRequestMapper: TopicRequestMapper,
    private val topicResponseListMapper: TopicResponseListMapper
) {

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
            responses = mutableListOf(),
            status = StatusTopic.NO_RESPONSE
        )

        val responses = ResponseTopic(
            1,
            "Respndido",
            LocalDateTime.now(),
            user,
            topic = topic,
            true
        )
        val topicTwo = Topic(
            id = 2,
            title = "Duvida em Java",
            message = "Estou com problema em java",
            dateTimeCreated = LocalDateTime.now(),
            course = course,
            author = user,
            responses = mutableListOf(responses),
            status = StatusTopic.NO_RESPONSE
        )

        val topicThree = Topic(
            id = 3,
            title = "Duvida em Java",
            message = "Estou com problema em java",
            dateTimeCreated = LocalDateTime.now(),
            course = course,
            author = user,
            responses = mutableListOf(responses),
            status = StatusTopic.NO_RESPONSE
        )

        topics = arrayListOf(topic, topicTwo, topicThree)
    }

    fun list(): List<TopicResponseDTO> {
        return topicResponseListMapper.map(topics)
    }

    fun getTopicById(id: Long): Topic {
        val topic = topics.first { topic -> topic.id == id }
        return topic
    }

    fun getResponseTopicByTopicId(id: Long): List<ResponseTopic> {
        return topics
            .first { topic -> topic.id == id }
            .responses
    }

    fun save(topicRequestDTO: TopicRequestDTO): Topic {
        val topic = topicRequestMapper.map(topicRequestDTO)
        topics = topics.plus(topic)

        return topic
    }

    fun update(topicUpdateRequestdto: TopicUpdateRequestDTO) {
        val topic = getTopicById(topicUpdateRequestdto.id)
        topics = topics.minus(topic).plus(
            Topic(
                id = topic.id,
                title = topicUpdateRequestdto.title,
                message = topicUpdateRequestdto.message,
                responses = topic.responses,
                dateTimeCreated = topic.dateTimeCreated,
                author = topic.author,
                course = topic.course,
                status = topic.status
            )
        )
    }

    fun removeById(id: Long) {
        val topic = getTopicById(id)
        topics = topics.minus(topic)
    }
}