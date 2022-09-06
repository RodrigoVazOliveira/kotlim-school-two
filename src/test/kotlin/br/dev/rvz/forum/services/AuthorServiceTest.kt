package br.dev.rvz.forum.services

import br.dev.rvz.forum.mappers.authors.AuthorModelRequestMapper
import br.dev.rvz.forum.models.User
import br.dev.rvz.forum.models.dto.AuthorSavedDTO
import br.dev.rvz.forum.repositories.AuthorRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class AuthorServiceTest {


    private val authorRepository: AuthorRepository = mockk()
    private val authorModelRequestMapper: AuthorModelRequestMapper = mockk()

    val authorService: AuthorService = AuthorService(
        authorRepository, authorModelRequestMapper
    )

    @Test
    fun `dev salvar um author no banco`() {
        val userSaved: User = User(
            id = 1,
            name = "Jorge",
            email = "jorge@gmail.com",
            password = "12345",
            role = listOf()
        )

        val user = User(
            id = null,
            name = "Jorge",
            email = "jorge@gmail.com",
            password = "12345",
            role = listOf()
        )

        every { authorRepository.save(any()) } returns userSaved
        every { authorModelRequestMapper.map(any()) } returns user
        val actual = authorService.save(
            AuthorSavedDTO(
                name = "Jorge",
                email = "jorge@gmail.com",
                password = "123456"
            )
        )

        verify(exactly = 1) { authorRepository.save(any()) }
        verify(exactly = 1) { authorModelRequestMapper.map(any()) }
    }
}