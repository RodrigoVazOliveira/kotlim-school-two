package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.configurations.jwt.JWTGenerateToken
import br.dev.rvz.forum.models.UserTest
import br.dev.rvz.forum.models.dto.CourseSaveDTO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
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
        private const val RESOURCE = "/courses"
    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()

    }


    @Test
    fun `Deve cadastrar um novo curso e retornar 201`() {
        val courseSaveDTO = CourseSaveDTO("Curso de teste", "Teste")
        val token = generateToken()
        mockMvc.post(RESOURCE) {
            headers { token?.let { this.setBearerAuth(token) } }
            this.contentType = MediaType.APPLICATION_JSON
            this.content = jacksonObjectMapper().writeValueAsString(courseSaveDTO)
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun generateToken(): String? {
        val authorities = mutableListOf<SimpleGrantedAuthority>(SimpleGrantedAuthority("LEITURA_ESCRITA"))
        val user = UserTest.buildSaved()

        return jwtGenerateToken.getToken(user.email, authorities)
    }

}