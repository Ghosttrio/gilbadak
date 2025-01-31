package com.ghosttrio.gilbadak.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDSLConfig {

    /**
     * 스프링에서 @Autowired나 @PersistenceContext와 같이 DI로 주입받을 때
     * 변수를 선언하면서 바로 초기화할 필요는 없다.
     * 대신 스프링이 객체를 주입할 때 해당 변수가 초기화되므로 lateinit을 사용하여 나중에 초기화될 것을 명시할 수 있다.
     */
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Bean
    fun jpaQueryFactory() = JPAQueryFactory(entityManager)


}