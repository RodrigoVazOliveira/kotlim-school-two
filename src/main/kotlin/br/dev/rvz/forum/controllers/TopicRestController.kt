package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.TopicRequestDTO
import br.dev.rvz.forum.models.dto.TopicResponseDTO
import br.dev.rvz.forum.services.TopicService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicRestController(val topicService: TopicService) {
    private val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping
    fun list(): List<TopicResponseDTO> {
        return topicService.list()
    }

    @GetMapping("/{id}")
    fun getTopicById(@PathVariable id: Long): TopicResponseDTO {
        return topicService.getTopicById(id)
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