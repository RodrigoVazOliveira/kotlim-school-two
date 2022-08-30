package br.dev.rvz.forum.mappers.topics

import br.dev.rvz.forum.mappers.Mapper
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.topics.TopicResponseDTO
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