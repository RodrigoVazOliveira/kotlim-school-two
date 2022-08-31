package br.dev.rvz.forum.services

import br.dev.rvz.forum.exceptions.NotFoundException
import br.dev.rvz.forum.mappers.authors.AuthorModelRequestMapper
import br.dev.rvz.forum.models.User
import br.dev.rvz.forum.models.dto.AuthorSavedDTO
import br.dev.rvz.forum.repositories.AuthorRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,
    private val authorModelRequestMapper: AuthorModelRequestMapper
) {

    fun save(authorSaveDTO: AuthorSavedDTO): User {
        val author = authorModelRequestMapper.map(authorSaveDTO)
        return authorRepository.save(author)
    }

    fun findById(id: Long): User {
        val authorOptional = authorRepository.findById(id)

        if (authorOptional.isEmpty) {
            throw NotFoundException("Nao foi localizado o author com id $id")
        }

        return authorOptional.get()
    }
}