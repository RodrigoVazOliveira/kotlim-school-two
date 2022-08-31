package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.models.Course
import br.dev.rvz.forum.models.dto.CourseSaveDTO
import br.dev.rvz.forum.services.CourseService
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/courses")
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