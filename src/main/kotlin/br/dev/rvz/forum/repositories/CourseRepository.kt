package br.dev.rvz.forum.repositories

import br.dev.rvz.forum.models.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Long> {
}