package br.dev.rvz.forum.models.dto

import br.dev.rvz.forum.models.User
import kotlin.random.Random

class AuthorSavedDTO(val name: String, val email: String) {
    fun convertDTOToModel(): User {
        return User(
            id = Random.nextLong(0, 20),
            name = name,
            email = email
        )
    }


}
