package br.dev.rvz.forum.services

import br.dev.rvz.forum.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val user: User) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val roles: MutableList<SimpleGrantedAuthority> = mutableListOf()
        user.role.forEach { roles.add(SimpleGrantedAuthority(it.name)) }

        return roles
    }

    override fun getPassword() = user.password


    override fun getUsername() = user.email


    override fun isAccountNonExpired() = true


    override fun isAccountNonLocked() = true


    override fun isCredentialsNonExpired() = true


    override fun isEnabled() = true
}