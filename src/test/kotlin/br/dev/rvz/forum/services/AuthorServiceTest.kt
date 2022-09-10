package br.dev.rvz.forum.services

import br.dev.rvz.forum.exceptions.NotFoundException
import br.dev.rvz.forum.mappers.authors.AuthorModelRequestMapper
import br.dev.rvz.forum.models.User
import br.dev.rvz.forum.models.UserTest
import br.dev.rvz.forum.models.dto.AuthorSavedDTO
import br.dev.rvz.forum.repositories.AuthorRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class AuthorServiceTest {
    private val authorRepository: AuthorRepository = mockk()
    private val authorModelRequestMapper: AuthorModelRequestMapper = mockk()

    private val authorService: AuthorService = AuthorService(
        authorRepository, authorModelRequestMapper
    )

    @Test
    fun `dev salvar um author no banco`() {
        val userSaved: User = UserTest.buildSaved()
        val user = UserTest.buildNotSave()

        every { authorRepository.save(any()) } returns userSaved
        every { authorModelRequestMapper.map(any()) } returns user
        authorService.save(
            AuthorSavedDTO(
                name = "Jorge",
                email = "jorge@gmail.com",
                password = "123456",
                roles = listOf()
            )
        )

        verify(exactly = 1) { authorRepository.save(any()) }
        verify(exactly = 1) { authorModelRequestMapper.map(any()) }
    }

    @Test
    fun `buscar um autor por id com sucesso`() {
        val user: User = UserTest.buildSaved()
        every { authorRepository.findById(any()) } returns Optional.of(user)

        authorService.findById(1)

        verify(exactly = 1) { authorRepository.findById(any()) }
    }

    @Test
    fun `buscar author por id sem sucesso`() {
        every { authorRepository.findById(any()) } returns Optional.empty()
        val actual = assertThrows<NotFoundException> {
            authorService.findById(1)
        }

        assertThat(actual.message).isEqualTo("Nao foi localizado o author com id 1")
    }
}