package br.dev.rvz.forum.controllers

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CourseRestControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext
    private lateinit var mockMvc: MockMvc

    companion object {
        private const val resource = "/topics"
    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()

    }

    @Test
    fun `deve retornar codigo 403 quando chamar topicos sem authenticacao`() {
        mockMvc.get(resource).andExpect { status { is4xxClientError() } }
    }
}