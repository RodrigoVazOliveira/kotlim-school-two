package br.dev.rvz.forum.mappers

import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.TopicResponseDTO
import org.springframework.stereotype.Component

@Component
class TopicResponseMapper : Mapper<Topic, TopicResponseDTO> {
    override fun map(topic: Topic): TopicResponseDTO {
        return TopicResponseDTO(
            id = topic.id,
            title = topic.title,
            message = topic.message,
            status = topic.status,
            dataCreated = topic.dateTimeCreated
        )
    }

}