package br.dev.rvz.forum.mappers.authors

import br.dev.rvz.forum.mappers.Mapper
import br.dev.rvz.forum.models.Role
import br.dev.rvz.forum.models.User
import br.dev.rvz.forum.models.dto.AuthorSavedDTO
import br.dev.rvz.forum.services.RoleService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthorModelRequestMapper(
    private val roleService: RoleService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : Mapper<AuthorSavedDTO, User> {
    override fun map(t: AuthorSavedDTO): User {
        val roles: MutableList<Role> = findRoleByName(t)
        return User(
            id = null,
            name = t.name,
            email = t.email,
            password = bCryptPasswordEncoder.encode(t.password),
            role = roles
        )
    }

    private fun findRoleByName(t: AuthorSavedDTO): MutableList<Role> {
        val roles: MutableList<Role> = mutableListOf()
        t.roles.forEach { name ->
            roles.add(roleService.findByName(name))
        }

        return roles
    }
}