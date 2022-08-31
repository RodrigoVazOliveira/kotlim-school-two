package br.dev.rvz.forum.repositories

import br.dev.rvz.forum.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<User, Long> {
}