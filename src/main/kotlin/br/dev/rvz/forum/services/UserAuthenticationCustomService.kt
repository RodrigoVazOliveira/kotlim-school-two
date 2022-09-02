package br.dev.rvz.forum.services

import br.dev.rvz.forum.exceptions.NotFoundException
import br.dev.rvz.forum.models.User
import br.dev.rvz.forum.repositories.AuthorRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserAuthenticationCustomService(private val authorRepository: AuthorRepository) : UserDetailsService {
    val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    fun findById(id: Long): User {
        val optionalUser = authorRepository.findById(id)
        if (optionalUser.isEmpty) {
            throw NotFoundException("Usuario nao localizado com id $id")
        }

        return optionalUser.get()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        LOGGER.info("loadUserByUsername - username: $username")
        if (username != null) {
            val user = findByEmail(username)
            LOGGER.info("user: {}", user)
            val userDetail = UserDetail(user)
            LOGGER.info("loadUserByUsername - userDetail : $userDetail")
            return userDetail
        }

        throw RuntimeException("campo username esta vazio")
    }

    private fun findByEmail(username: String): User {
        val userOptional = authorRepository.findByEmail(username)
        LOGGER.info("findByEmail - userOptional : ${userOptional.get()}")
        if (userOptional.isEmpty) {
            throw NotFoundException("usuario nao localizado com email $username")
        }

        return userOptional.get()
    }
}