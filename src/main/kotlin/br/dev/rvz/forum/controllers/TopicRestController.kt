package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.mappers.topics.TopicResponseMapper
import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.topics.TopicRequestDTO
import br.dev.rvz.forum.models.dto.topics.TopicResponseDTO
import br.dev.rvz.forum.models.dto.topics.TopicUpdateRequestDTO
import br.dev.rvz.forum.services.TopicService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicRestController(
    val topicService: TopicService,
    private val topicResponseMapper: TopicResponseMapper
) {
    private val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun list(): List<TopicResponseDTO> {
        return topicService.list()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTopicById(@PathVariable id: Long): TopicResponseDTO {
        return topicResponseMapper.map(topicService.getTopicById(id))
    }


    @GetMapping("/{id}/responses")
    @ResponseStatus(HttpStatus.OK)
    fun getResponsesByTopicId(@PathVariable id: Long): List<ResponseTopic> {
        return topicService.getResponseTopicByTopicId(id)
    }

    @PostMapping
    fun save(
        @RequestBody @Valid topicRequestDTO: TopicRequestDTO,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<Topic> {
        LOGGER.info("save - topicSaveDTO: {}", topicRequestDTO)
        val topic = topicService.save(topicRequestDTO)
        val uri = uriComponentsBuilder.path("/topics/${topic.id}").build().toUri()

        return ResponseEntity.created(uri).body(topic)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateTopicById(
        @RequestBody @Valid topicUpdateRequestdto: TopicUpdateRequestDTO
    ) {
        LOGGER.info("updateTopicById - topicUpdateRequestdto: {}", topicUpdateRequestdto)
        topicService.update(topicUpdateRequestdto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeTopic(@PathVariable id: Long) {
        topicService.removeById(id)
    }

}