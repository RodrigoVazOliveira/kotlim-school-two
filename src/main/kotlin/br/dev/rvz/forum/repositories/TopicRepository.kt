package br.dev.rvz.forum.repositories

import br.dev.rvz.forum.models.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository : JpaRepository<Topic, Long> {
    fun findByCourseName(name: String): List<Topic>
}