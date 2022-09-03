package br.dev.rvz.forum.models

import net.minidev.json.annotate.JsonIgnore
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
    val email: String,
    val password: String,

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_roles")
    val role: List<Role>
)