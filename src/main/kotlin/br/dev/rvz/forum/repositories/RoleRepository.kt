package br.dev.rvz.forum.repositories

import br.dev.rvz.forum.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@Repository
@RepositoryRestResource(path = "regras")
interface RoleRepository : JpaRepository<Role, Long> {
}