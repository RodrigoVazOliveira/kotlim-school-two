package br.dev.rvz.forum.mappers.authors

import br.dev.rvz.forum.mappers.Mapper
import br.dev.rvz.forum.models.User
import br.dev.rvz.forum.models.dto.AuthorSavedDTO
import org.springframework.stereotype.Component

@Component
class AuthorModelRequestMapper : Mapper<AuthorSavedDTO, User> {
    override fun map(t: AuthorSavedDTO): User {
        return User(
            id = null,
            name = t.name,
            email = t.email,
            password = t.password
        )
    }
}