package br.dev.rvz.forum.configurations.securities

import br.dev.rvz.forum.configurations.filters.JWTAuthenticationFilter
import br.dev.rvz.forum.configurations.filters.JWTVerifyAuthenticationFilter
import br.dev.rvz.forum.configurations.jwt.JWTGenerateToken
import br.dev.rvz.forum.configurations.jwt.JWTVerifyToken
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val jwtGenerateToken: JWTGenerateToken,
    private val jwtVerifyToken: JWTVerifyToken
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(
                JWTVerifyAuthenticationFilter(
                    authManager = http.getSharedObject(AuthenticationManager::class.java),
                    jwtGenerateToken = jwtGenerateToken
                ),
                UsernamePasswordAuthenticationFilter().javaClass
            )
            .addFilterBefore(
                JWTAuthenticationFilter(
                    jwtVerify = jwtVerifyToken
                ), OncePerRequestFilter::class.java
            )
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        return http.build()
    }

    @Bean
    fun getBcryptpasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}