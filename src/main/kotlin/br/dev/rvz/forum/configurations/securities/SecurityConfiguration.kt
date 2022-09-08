package br.dev.rvz.forum.configurations.securities

import br.dev.rvz.forum.configurations.filters.JWTAuthenticationFilter
import br.dev.rvz.forum.configurations.filters.JWTVerifyAuthenticationFilter
import br.dev.rvz.forum.configurations.jwt.JWTGenerateToken
import br.dev.rvz.forum.configurations.jwt.JWTVerifyToken
import br.dev.rvz.forum.services.UserAuthenticationCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val jwtGenerateToken: JWTGenerateToken,
    private val jwtVerifyToken: JWTVerifyToken,
    private val userAuthenticationCustomService: UserAuthenticationCustomService
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder.userDetailsService(userAuthenticationCustomService)
            .passwordEncoder(getBcryptpasswordEncoder())
        val authManager = authenticationManagerBuilder.build()
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers("/topics").hasAuthority("LEITURA_ESCRITA")
            .antMatchers("/topics/*/responses").hasAuthority("LEITURA_ESCRITA")
            .antMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .authenticationManager(authManager)
            .addFilterBefore(
                JWTVerifyAuthenticationFilter(
                    authManager = authManager,
                    jwtGenerateToken = jwtGenerateToken
                ),
                UsernamePasswordAuthenticationFilter().javaClass
            )
            .addFilterBefore(
                JWTAuthenticationFilter(
                    jwtVerify = jwtVerifyToken
                ), UsernamePasswordAuthenticationFilter().javaClass
                //OncePerRequestFilter::class.java
            )
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        return http.build()
    }

    @Bean
    fun getBcryptpasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}