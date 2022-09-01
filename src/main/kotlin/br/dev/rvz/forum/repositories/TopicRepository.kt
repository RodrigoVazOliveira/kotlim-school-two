package br.dev.rvz.forum.repositories

import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.models.dto.topics.TopicReportDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.stereotype.Repository

@Repository
@RepositoryRestResource(path = "topicos")
interface TopicRepository : JpaRepository<Topic, Long> {
    fun findByCourseName(name: String, pagenation: Pageable): Page<Topic>

    @Query("SELECT new br.dev.rvz.forum.models.dto.topics.TopicReportDTO(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun reports(): List<TopicReportDTO>

    @RestResource(exported = false)
    override fun deleteById(id: Long) {

    }

    @RestResource(exported = false)
    override fun delete(entity: Topic) {
        TODO("Not yet implemented")
    }
}