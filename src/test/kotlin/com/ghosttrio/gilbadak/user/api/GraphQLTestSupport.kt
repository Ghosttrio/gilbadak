package com.ghosttrio.gilbadak.user.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.graphql.test.tester.GraphQlTester

@SpringBootTest
@AutoConfigureGraphQlTester
abstract class GraphQLTestSupport {
    @Autowired
    protected lateinit var graphQlTester: GraphQlTester
}