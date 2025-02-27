package com.ghosttrio.gilbadak.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

/**
 *  토큰 검증
 *  흐름 : JWT 토큰 검증 -> 인증 정보 저장 -> 시큐리티 url 검증 -> api 호출
 *
 *  1. 토큰 없음, 허용되지 않은 url -> 403 forbidden
 *  2. 토큰 없음, 허용된 url -> 200 ok
 *  3. 토큰 있음, 허용되지 않은 url, 토큰 검증 성공 -> 200 ok
 *  4. 토큰 있음, 허용되지 않은 url, 토큰 검증 실패 -> 401 unauthorization
 *  4. 토큰 있음, 허용된 url, 토큰 검증 성공 -> 허용된 url인데 토큰이 있어서 문제가 있음 -> 토큰 필요 없음
 *  5. 토큰 있음, 허용된 url, 토큰 검증 실패 -> 허용된 url인데 토큰이 있어서 문제가 있음 -> 토큰 필요 없음
 *
 *  정리
 *  1. 토큰 검증 성공 -> Security Context 저장 -> 허용되지 않은 url(인증 정보 있으니 통과) -> api 호출
 *  2. 토큰 검증 성공 -> Security Context 저장 -> 허용된 url(무의미 하니 통과, 클라이언트 문제) -> api 호출
 *  3. 토큰 검증 실패 -> error
 *  4. 토큰 없을 때 -> 허용되지 않은 url -> error
 *  5. 토큰 없을 때 -> 허용된 url -> api 호출
 *
 */

class JwtFilter : OncePerRequestFilter() {

    private val log = KotlinLogging.logger {}

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        log.info { "JWT 필터 시작" }
        val accessToken = request.getHeader("Authorization")
        if (StringUtils.hasText(accessToken)) {
            println(accessToken)
            log.info { "JWT 토큰 있음" }
            val authorities: Collection<GrantedAuthority> = listOf()
            val authentication: Authentication = UsernamePasswordAuthenticationToken(Object(), Object(), authorities)
            SecurityContextHolder.getContext().authentication = authentication
        } else {
            log.info { "JWT 토큰이 없습니다." }
        }
        filterChain.doFilter(request, response)
    }
}