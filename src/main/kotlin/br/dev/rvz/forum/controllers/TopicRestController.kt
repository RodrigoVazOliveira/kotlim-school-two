package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.mappers.topics.TopicResponseMapper
import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.topics.TopicRequestDTO
import br.dev.rvz.forum.models.dto.topics.TopicResponseDTO
import br.dev.rvz.forum.services.TopicService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicRestController(
    val topicService: TopicService,
    private val topicResponseMapper: TopicResponseMapper,
) {
    private val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping
    fun list(): List<TopicResponseDTO> {
        return topicService.list()
    }

    @GetMapping("/{id}")
    fun getTopicById(@PathVariable id: Long): TopicResponseDTO {
        return topicResponseMapper.map(topicService.getTopicById(id))
    }


    @GetMapping("/{id}/responses")
    fun getResponsesByTopicId(@PathVariable id: Long): List<ResponseTopic> {
        return topicService.getResponseTopicByTopicId(id)
    }

    @PostMapping
    fun save(@RequestBody @Valid topicRequestDTO: TopicRequestDTO): Topic {
        LOGGER.info("save - topicSaveDTO: {}", topicRequestDTO)
        return topicService.save(topicRequestDTO)
    }
}