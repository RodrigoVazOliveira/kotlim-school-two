package br.dev.rvz.forum.services

import br.dev.rvz.forum.mappers.courses.CourseRequestMapper
import br.dev.rvz.forum.repositories.CourseRepository
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class CourseServiceTest {
    private val courseRepository: CourseRepository = mockk()
    private val courseRequestMapper: CourseRequestMapper = mockk()

    @Test
    fun `salvar um curso com sucesso`() {
        
    }
}