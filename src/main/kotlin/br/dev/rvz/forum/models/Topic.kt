package br.dev.rvz.forum.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "topics")
data class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String,
    val message: String,
    val dateTimeCreated: LocalDateTime,

    @ManyToOne
    val course: Course,

    @ManyToOne
    val author: User,

    @Enumerated(value = EnumType.STRING)
    val status: StatusTopic,

    @OneToMany(mappedBy = "topic")
    val responses: MutableList<ResponseTopic>
)