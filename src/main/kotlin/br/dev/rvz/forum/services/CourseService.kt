package br.dev.rvz.forum.services

import br.dev.rvz.forum.models.Course
import br.dev.rvz.forum.models.dto.CourseSaveDTO
import org.springframework.stereotype.Service

@Service
class CourseService(var courses: List<Course>) {

    fun save(courseSaveDTO: CourseSaveDTO): Course {
        val course = courseSaveDTO.convertDTOToModel()
        courses = courses.plus(course)

        return course
    }

    fun findById(id: Long): Course {
        return courses.first { course -> course.id == id }
    }
}