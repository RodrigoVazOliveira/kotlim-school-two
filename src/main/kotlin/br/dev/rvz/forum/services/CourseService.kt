package br.dev.rvz.forum.services

import br.dev.rvz.forum.exceptions.NotFoundException
import br.dev.rvz.forum.mappers.courses.CourseRequestMapper
import br.dev.rvz.forum.models.Course
import br.dev.rvz.forum.models.dto.CourseSaveDTO
import br.dev.rvz.forum.repositories.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val courseRequestMapper: CourseRequestMapper
) {

    fun save(courseSaveDTO: CourseSaveDTO): Course {
        val course = courseRequestMapper.map(courseSaveDTO)
        return courseRepository.save(course)
    }

    fun findById(id: Long): Course {
        val courseOptional = courseRepository.findById(id)
        if (courseOptional.isEmpty) {
            throw NotFoundException("Nao foi localizado o curso com id $id")
        }

        return courseOptional.get()
    }
}