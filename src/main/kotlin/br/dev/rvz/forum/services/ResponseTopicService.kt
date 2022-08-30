package br.dev.rvz.forum.services

import br.dev.rvz.forum.mappers.responses.ResponseTopicRequestMapper
import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.dto.responses.ResponseTopicRequestDTO
import org.springframework.stereotype.Service

@Service
class ResponseTopicService(
    private val responseTopics: MutableList<ResponseTopic> = mutableListOf(),
    private val responseTopicRequestMapper: ResponseTopicRequestMapper
) {

    fun save(responseTopicRequestDTO: ResponseTopicRequestDTO): ResponseTopic {
        val responseTopic = responseTopicRequestMapper.map(responseTopicRequestDTO)
        responseTopics.add(responseTopic)
        responseTopic.topic.responses.add(responseTopic)

        return responseTopic
    }
}