package br.dev.rvz.forum.services

import br.dev.rvz.forum.exceptions.NotFoundException
import br.dev.rvz.forum.mappers.responses.ResponseTopicRequestMapper
import br.dev.rvz.forum.mappers.responses.UpdateResponseTopicResponseMapper
import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.dto.responses.ResponseTopicRequestDTO
import br.dev.rvz.forum.models.dto.responses.ResponseTopicResponseDTO
import br.dev.rvz.forum.models.dto.responses.UpdateResponseTopicRequestDTO
import br.dev.rvz.forum.repositories.ResponseTopicRepository
import org.springframework.stereotype.Service

@Service
class ResponseTopicService(
    private val responseTopicRepository: ResponseTopicRepository,
    private val responseTopicRequestMapper: ResponseTopicRequestMapper,
    private val updateResponseTopicResponseMapper: UpdateResponseTopicResponseMapper
) {

    fun save(responseTopicRequestDTO: ResponseTopicRequestDTO): ResponseTopic {
        val responseTopic = responseTopicRequestMapper.map(responseTopicRequestDTO)

        return responseTopicRepository.save(responseTopic)
    }

    fun update(updateResponseTopicRequestDTO: UpdateResponseTopicRequestDTO): ResponseTopicResponseDTO {
        val responseTopic = getById(updateResponseTopicRequestDTO.id)
        val updateResponseTopic = ResponseTopic(
            id = responseTopic.id,
            message = updateResponseTopicRequestDTO.message,
            dateTimeCreated = responseTopic.dateTimeCreated,
            topic = responseTopic.topic,
            author = responseTopic.author,
            soluction = responseTopic.soluction
        )


        return updateResponseTopicResponseMapper.map(responseTopicRepository.save(updateResponseTopic))
    }

    fun getById(id: Long): ResponseTopic {
        val responseTopicOptional = responseTopicRepository.findById(id)
        if (responseTopicOptional.isEmpty) {
            throw NotFoundException("Nao foi localizado resposta com id $id")
        }

        return responseTopicOptional.get()
    }

    fun deleteById(idResponseTopic: Long) {
        val responseTopic = getById(idResponseTopic)
        responseTopicRepository.delete(responseTopic)
    }
}