package br.dev.rvz.forum.mappers

import br.dev.rvz.forum.models.StatusTopic
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.TopicRequestDTO
import br.dev.rvz.forum.services.AuthorService
import br.dev.rvz.forum.services.CourseService
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import kotlin.random.Random

@Component
class TopicRequestMapper(
    private val authorService: AuthorService,
    private val courseService: CourseService
) : Mapper<TopicRequestDTO, Topic> {
    override fun map(topicRequestDTO: TopicRequestDTO): Topic {
        return Topic(
            author = authorService.findById(topicRequestDTO.idAuthor),
            course = courseService.findById(topicRequestDTO.idCourse),
            title = topicRequestDTO.title,
            message = topicRequestDTO.message,
            responses = listOf(),
            dateTimeCreated = LocalDateTime.now(),
            id = Random.nextLong(0, 30),
            status = StatusTopic.NO_RESPONSE
        )
    }
}