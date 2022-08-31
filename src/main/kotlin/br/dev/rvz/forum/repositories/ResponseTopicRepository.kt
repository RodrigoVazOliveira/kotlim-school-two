package br.dev.rvz.forum.repositories

import br.dev.rvz.forum.models.ResponseTopic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResponseTopicRepository : JpaRepository<ResponseTopic, Long> {
}