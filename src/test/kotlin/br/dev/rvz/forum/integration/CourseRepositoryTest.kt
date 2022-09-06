package br.dev.rvz.forum.integration

import br.dev.rvz.forum.repositories.CourseRepository
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
internal class CourseRepositoryTest {
    @Autowired
    private lateinit var courseRepository: CourseRepository

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


}