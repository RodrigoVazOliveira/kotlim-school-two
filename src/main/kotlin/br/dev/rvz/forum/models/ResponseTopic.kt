package br.dev.rvz.forum.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "responses_topic")
data class ResponseTopic(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val message: String,

    @Column(name = "date_time_created")
    val dateTimeCreated: LocalDateTime,

    @ManyToOne
    val author: User,

    @ManyToOne
    val topic: Topic,
    val soluction: Boolean
)
