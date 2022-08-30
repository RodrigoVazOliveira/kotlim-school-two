package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.models.User
import br.dev.rvz.forum.models.dto.AuthorSavedDTO
import br.dev.rvz.forum.services.AuthorService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authors")
class AuthorRestController(val authorService: AuthorService) {
    val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    fun save(@RequestBody authorSavedDTO: AuthorSavedDTO): User {
        LOGGER.info("save - authorSavedDTO: {}", authorSavedDTO)

        return authorService.save(authorSavedDTO)
    }
}