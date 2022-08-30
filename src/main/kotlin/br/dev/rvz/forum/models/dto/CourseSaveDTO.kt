package br.dev.rvz.forum.models.dto

import br.dev.rvz.forum.models.Course
import kotlin.random.Random

data class CourseSaveDTO(val name: String, val category: String) {

    fun convertDTOToModel(): Course {
        return Course(
            id = Random.nextLong(0, 10),
            name = name,
            category = category
        )
    }

}