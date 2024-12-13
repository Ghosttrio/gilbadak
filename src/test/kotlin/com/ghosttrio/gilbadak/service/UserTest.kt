package com.ghosttrio.gilbadak.service

import com.ghosttrio.gilbadak.user.entity.UserEntity
import com.ghosttrio.gilbadak.user.repository.UserRepository
import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.graphql.test.tester.GraphQlTester

@AutoConfigureGraphQlTester
@SpringBootTest
class UserTest(
    private val graphQlTester: GraphQlTester,
    private val userRepository: UserRepository
) : StringSpec({

    "유저 전체 조회" {
        userRepository.save(UserEntity(email = "test", nickname = "test"))
        val document = """
                {
                    loadAllUser {
                        id
                        email
                        nickname
                        status
                    }
                }
            """.trimIndent()
        graphQlTester.document(document)
            .execute()
            .path("loadAllUser[*].email")
            .entityList(String::class.java)
            .containsExactly("test")
    }
})