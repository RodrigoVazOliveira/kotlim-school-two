package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.models.User
import br.dev.rvz.forum.models.dto.AuthorSavedDTO
import br.dev.rvz.forum.services.AuthorService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/authors")
@SecurityRequirement(name = "bearerAuth")
class AuthorRestController(val authorService: AuthorService) {
    val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    @Transactional
    fun save(
        @RequestBody @Valid authorSavedDTO: AuthorSavedDTO,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<User> {
        LOGGER.info("save - authorSavedDTO: {}", authorSavedDTO)
        val user = authorService.save(authorSavedDTO)
        val uriLocation = uriComponentsBuilder.path("/authors/${user.id}").build().toUri()

        return ResponseEntity.created(uriLocation).body(user)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): User {
        LOGGER.info("getById - id: $id")
        val user = authorService.findById(id)

        return user
    }
}