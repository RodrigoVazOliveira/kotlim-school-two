package br.dev.rvz.forum.services

import br.dev.rvz.forum.exceptions.NotFoundException
import br.dev.rvz.forum.models.Role
import br.dev.rvz.forum.repositories.RoleRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val roleRepository: RoleRepository
) {
    private final val logger: Logger = LoggerFactory.getLogger(RoleService::class.java)

    fun findByName(name: String): Role {
        logger.info("findByName - name: $name")
        val roleOptional = roleRepository.findByName(name)
        if (roleOptional.isEmpty) {
            logger.error("findByName - role nao encontrada!")
            throw NotFoundException("Regra de seguranca nao localizada!")
        }

        logger.info("findByName - role: ${roleOptional.get()}")
        return roleOptional.get()
    }

}