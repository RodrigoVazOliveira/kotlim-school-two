package br.dev.rvz.forum.integration

import br.dev.rvz.forum.models.TopicTest
import br.dev.rvz.forum.models.dto.topics.TopicReportDTO
import br.dev.rvz.forum.repositories.AuthorRepository
import br.dev.rvz.forum.repositories.CourseRepository
import br.dev.rvz.forum.repositories.TopicRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MariaDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class TopicRepositoryTest(
    @Autowired
    private val courseRepository: CourseRepository,
    @Autowired
    private val authorRepository: AuthorRepository
) {
    @Autowired
    private lateinit var topicRepository: TopicRepository


    companion object {
        @Container
        private val mariadbContainer: MariaDBContainer<Nothing> = MariaDBContainer<Nothing>(
            "mariadb:latest"
        ).apply {
            withDatabaseName("forum_kotlim_test")
            withUsername("fk")
            withPassword("kotlim")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mariadbContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mariadbContainer::getUsername)
            registry.add("spring.datasource.password", mariadbContainer::getPassword)
        }
    }

    @Test
    fun `deve gerar um relatorio`() {
        val course = courseRepository.findById(1)
        val author = authorRepository.findById(1)
        topicRepository.save(TopicTest.buildSave(author = author.get(), course = course.get()))
        val reports = topicRepository.reports()

        assertThat(reports).isNotNull
        assertThat(reports.first()).isExactlyInstanceOf(TopicReportDTO::class.java)
    }
}