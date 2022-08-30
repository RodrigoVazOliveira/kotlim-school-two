package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.dto.responses.ResponseTopicRequestDTO
import br.dev.rvz.forum.services.ResponseTopicService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topics/{id}/responses")
class TopicResponseRestController(
    private val responseTopicService: ResponseTopicService
) {
    private val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    fun save(
        @PathVariable id: Long,
        @RequestBody @Valid responseTopicRequestDTO: ResponseTopicRequestDTO
    ): ResponseTopic {
        responseTopicRequestDTO.idTopic = id
        LOGGER.info("save - id: {}, responseTopicRequestDTO: {}", id, responseTopicRequestDTO)
        return responseTopicService.save(responseTopicRequestDTO)
    }
}