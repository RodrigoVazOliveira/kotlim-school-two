package br.dev.rvz.forum.models


import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "responses_topic")
class ResponseTopic(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val message: String,

    @Column(name = "date_time_created")
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    val dateTimeCreated: LocalDateTime,

    @ManyToOne
    val author: User,

    @JsonIgnore
    @ManyToOne
    val topic: Topic,
    val soluction: Boolean
) {
    override fun toString(): String {
        return jacksonObjectMapper().writeValueAsString(this)
    }
}
