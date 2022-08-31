package br.dev.rvz.forum.models

import org.springframework.data.annotation.Immutable
import javax.persistence.*

@Entity
@Immutable
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val email: String
)