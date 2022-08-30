package br.dev.rvz.forum.mappers

import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.TopicResponseDTO
import org.springframework.stereotype.Component

@Component
class TopicResponseListMapper(
    private val topicResponseMapper: TopicResponseMapper
) : Mapper<List<Topic>, List<TopicResponseDTO>> {
    override fun map(topics: List<Topic>): List<TopicResponseDTO> {
        val topicResponseDTOs: MutableList<TopicResponseDTO> = mutableListOf()
        topics.forEach { topic -> topicResponseDTOs.add(topicResponseMapper.map(topic)) }

        return topicResponseDTOs
    }
}