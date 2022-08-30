package br.dev.rvz.forum.mappers.responses

import br.dev.rvz.forum.mappers.Mapper
import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.dto.responses.ResponseTopicRequestDTO
import br.dev.rvz.forum.services.AuthorService
import br.dev.rvz.forum.services.TopicService
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import kotlin.random.Random

@Component
class ResponseTopicRequestMapper(
    private val topicService: TopicService,
    private val authorService: AuthorService
) : Mapper<ResponseTopicRequestDTO, ResponseTopic> {
    override fun map(responseTopicRequestDTO: ResponseTopicRequestDTO): ResponseTopic {
        val author = authorService.findById(responseTopicRequestDTO.idAuthor)
        val topic = topicService.getTopicById(responseTopicRequestDTO.idTopic)

        return ResponseTopic(
            id = Random.nextLong(0, 30),
            message = responseTopicRequestDTO.message,
            topic = topic,
            author = author,
            soluction = false,
            dateTimeCreated = LocalDateTime.now()
        )
    }
}