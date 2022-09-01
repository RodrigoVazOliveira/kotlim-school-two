package br.dev.rvz.forum.mappers.topics

import br.dev.rvz.forum.mappers.Mapper
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.topics.TopicResponseDTO
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class TopicResponseListMapper(
    private val topicResponseMapper: TopicResponseMapper
) : Mapper<Page<Topic>, Page<TopicResponseDTO>> {
    override fun map(t: Page<Topic>): Page<TopicResponseDTO> {
        return t.map { topic -> topicResponseMapper.map(topic) }
    }

}