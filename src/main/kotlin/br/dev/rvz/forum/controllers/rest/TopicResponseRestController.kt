package br.dev.rvz.forum.controllers.rest

import br.dev.rvz.forum.models.ResponseTopic
import br.dev.rvz.forum.models.dto.responses.ResponseTopicRequestDTO
import br.dev.rvz.forum.models.dto.responses.ResponseTopicResponseDTO
import br.dev.rvz.forum.models.dto.responses.UpdateResponseTopicRequestDTO
import br.dev.rvz.forum.services.ResponseTopicService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics/{id}/responses")
@SecurityRequirement(name = "bearerAuth")
class TopicResponseRestController(
    private val responseTopicService: ResponseTopicService
) {
    private val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    @Transactional
    fun save(
        @PathVariable id: Long,
        @RequestBody @Valid responseTopicRequestDTO: ResponseTopicRequestDTO,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<ResponseTopic> {
        responseTopicRequestDTO.idTopic = id
        LOGGER.info("save - id: $id, responseTopicRequestDTO: {}", responseTopicRequestDTO)
        val responseTopic = responseTopicService.save(responseTopicRequestDTO)
        val uriLocation = uriComponentsBuilder.path("/topics/$id/responses/${responseTopic.id}").build().toUri()

        return ResponseEntity.created(uriLocation).body(responseTopic)
    }

    @PutMapping
    @Transactional
    fun updateResponseTopic(
        @PathVariable id: Long,
        @RequestBody @Valid updateResponseTopicRequestDTO: UpdateResponseTopicRequestDTO
    ): ResponseEntity<ResponseTopicResponseDTO> {
        val responsetopicResponseDTO = responseTopicService.update(updateResponseTopicRequestDTO)

        return ResponseEntity.ok(responsetopicResponseDTO)
    }

    @DeleteMapping("/{idResponseTopic}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleteById(@PathVariable id: Long, @PathVariable idResponseTopic: Long) {
        LOGGER.info("deleteById - id: $id, idResponseTopic: $idResponseTopic")
        responseTopicService.deleteById(idResponseTopic)
    }
}