package br.dev.rvz.forum.services

import br.dev.rvz.forum.exceptions.NotFoundException
import br.dev.rvz.forum.mappers.topics.TopicRequestMapper
import br.dev.rvz.forum.mappers.topics.TopicResponseListMapper
import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.topics.TopicRequestDTO
import br.dev.rvz.forum.models.dto.topics.TopicResponseDTO
import br.dev.rvz.forum.models.dto.topics.TopicUpdateRequestDTO
import br.dev.rvz.forum.repositories.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val topicRequestMapper: TopicRequestMapper,
    private val topicResponseListMapper: TopicResponseListMapper
) {


    fun list(nameCourse: String?, pagenation: Pageable): Page<TopicResponseDTO> {
        val topics = if (nameCourse != null) {
            topicRepository.findByCourseName(nameCourse, pagenation)
        } else {
            topicRepository.findAll(pagenation)
        }

        return topicResponseListMapper.map(topics)
    }

    fun getTopicById(id: Long): Topic {
        val topicOptional = topicRepository.findById(id)
        if (topicOptional.isEmpty) {
            throw NotFoundException("topico com $id nao existe")
        }

        return topicOptional.get()
    }

    fun getResponseTopicByTopicId(id: Long): List<ResponseTopic> {
        val topic = getTopicById(id)
        return topic.responses
    }

    fun save(topicRequestDTO: TopicRequestDTO): Topic {
        val topic = topicRequestMapper.map(topicRequestDTO)

        return topicRepository.save(topic)
    }

    fun update(topicUpdateRequestdto: TopicUpdateRequestDTO) {
        val topic = getTopicById(topicUpdateRequestdto.id)
        val updateTopic = Topic(
            id = topic.id,
            title = topicUpdateRequestdto.title,
            message = topicUpdateRequestdto.message,
            responses = topic.responses,
            dateTimeCreated = topic.dateTimeCreated,
            author = topic.author,
            course = topic.course,
            status = topic.status
        )

        topicRepository.save(updateTopic)
    }

    fun removeById(id: Long) {
        val topic = getTopicById(id)
        topicRepository.delete(topic)
    }
}