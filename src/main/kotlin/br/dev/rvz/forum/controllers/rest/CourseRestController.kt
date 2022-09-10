package br.dev.rvz.forum.controllers.rest

import br.dev.rvz.forum.models.Course
import br.dev.rvz.forum.models.dto.CourseSaveDTO
import br.dev.rvz.forum.services.CourseService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearerAuth")
class CourseRestController(
    val courseService: CourseService
) {
    private val LOGGER = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    @Transactional
    fun save(@RequestBody courseSaveDTO: CourseSaveDTO): Course {
        LOGGER.info("save : {}", courseSaveDTO)

        return courseService.save(courseSaveDTO)
    }
}