package br.dev.rvz.forum.models

import javax.persistence.*

@Entity
@Table(name = "courses")
data class Course(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val category: String
)
