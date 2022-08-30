package br.dev.rvz.forum.services

import br.dev.rvz.forum.models.User
import br.dev.rvz.forum.models.dto.AuthorSavedDTO
import org.springframework.stereotype.Service

@Service
class AuthorService(var authors: List<User>) {

    fun save(authorSaveDTO: AuthorSavedDTO): User {
        val author = authorSaveDTO.convertDTOToModel()
        authors = authors.plus(author)

        return author
    }

    fun findById(id: Long): User {
        return authors.first { user -> user.id == id }
    }
}