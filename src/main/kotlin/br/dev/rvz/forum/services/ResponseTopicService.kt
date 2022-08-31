package br.dev.rvz.forum.services

import br.dev.rvz.forum.mappers.responses.ResponseTopicRequestMapper
import br.dev.rvz.forum.mappers.responses.UpdateResponseTopicResponseMapper
import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.dto.responses.ResponseTopicRequestDTO
import br.dev.rvz.forum.models.dto.responses.ResponseTopicResponseDTO
import br.dev.rvz.forum.models.dto.responses.UpdateResponseTopicRequestDTO
import org.springframework.stereotype.Service

@Service
class ResponseTopicService(
    private val responseTopics: MutableList<ResponseTopic> = mutableListOf(),
    private val responseTopicRequestMapper: ResponseTopicRequestMapper,
    private val updateResponseTopicResponseMapper: UpdateResponseTopicResponseMapper
) {

    fun save(responseTopicRequestDTO: ResponseTopicRequestDTO): ResponseTopic {
        val responseTopic = responseTopicRequestMapper.map(responseTopicRequestDTO)
        responseTopics.add(responseTopic)
        responseTopic.topic.responses.add(responseTopic)

        return responseTopic
    }

    fun update(updateResponseTopicRequestDTO: UpdateResponseTopicRequestDTO): ResponseTopicResponseDTO {
        val responseTopic = responseTopics.first { it.id == updateResponseTopicRequestDTO.id }
        val updateResponseTopic = ResponseTopic(
            id = responseTopic.id,
            message = updateResponseTopicRequestDTO.message,
            dateTimeCreated = responseTopic.dateTimeCreated,
            topic = responseTopic.topic,
            author = responseTopic.author,
            soluction = responseTopic.soluction
        )
        responseTopics.minus(responseTopic)
        responseTopics.add(updateResponseTopic)

        return updateResponseTopicResponseMapper.map(updateResponseTopic)
    }

    fun deleteById(idResponseTopic: Long) {
        val responseTopic = responseTopics.first { it.id == idResponseTopic }
        responseTopic.topic.responses.minus(responseTopic)
        responseTopics.remove(responseTopic)
    }
}