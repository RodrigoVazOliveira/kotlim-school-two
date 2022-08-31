package br.dev.rvz.forum.mappers.responses

import br.dev.rvz.forum.mappers.Mapper
import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.dto.responses.ResponseTopicResponseDTO
import org.springframework.stereotype.Component

@Component
class UpdateResponseTopicResponseMapper : Mapper<ResponseTopic, ResponseTopicResponseDTO> {
    override fun map(topicRequestDTO: ResponseTopic): ResponseTopicResponseDTO {
        return ResponseTopicResponseDTO(
            id = topicRequestDTO.id,
            message = topicRequestDTO.message,
            soluction = topicRequestDTO.soluction
        )
    }
}