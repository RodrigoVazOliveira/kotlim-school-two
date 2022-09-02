package br.dev.rvz.forum.repositories

import br.dev.rvz.forum.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@RepositoryRestResource(path = "autores")
interface AuthorRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>
}