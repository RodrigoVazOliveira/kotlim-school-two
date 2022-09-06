package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.configurations.jwt.JWTGenerateToken
import br.dev.rvz.forum.models.UserTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.authority.SimpleGrantedAuthority
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

    @Autowired
    private lateinit var jwtGenerateToken: JWTGenerateToken

    companion object {
        private const val RESOURCE = "/topics"
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
        mockMvc.get(RESOURCE).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `deve retornar codigo 200 quando chamar topicos com token`() {
        val token = generateToken()
        mockMvc.get(RESOURCE) {
            this.headers { token?.let { this.setBearerAuth(it) } }
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun generateToken(): String? {
        val authorities = mutableListOf<SimpleGrantedAuthority>(SimpleGrantedAuthority("LEITURA_ESCRITA"))
        val user = UserTest.buildSaved()

        return jwtGenerateToken.getToken(user.email, authorities)
    }

}