package br.dev.rvz.forum.mappers.courses

import br.dev.rvz.forum.mappers.Mapper
import br.dev.rvz.forum.models.Course
import br.dev.rvz.forum.models.dto.CourseSaveDTO
import org.springframework.stereotype.Component

@Component
class CourseRequestMapper : Mapper<CourseSaveDTO, Course> {
    override fun map(t: CourseSaveDTO): Course {
        return Course(
            id = null,
            name = t.name,
            category = t.category
        )
    }

}