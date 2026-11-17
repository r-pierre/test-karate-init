package com.arc.gpts.tech.bdddemo.person;

import com.intuit.karate.junit5.Karate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.wiremock.spring.EnableWireMock;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

// WireMock starts on a random port with the Spring test context and loads
// the stubs from src/test/resources/wiremock/mappings.
@SpringBootTest(webEnvironment = RANDOM_PORT)
@EnableWireMock
class PersonTest {

    private static final String ROOT_PATH = "src/test/java/com/arc/gpts/tech/bdddemo/person/";

    @LocalServerPort
    private int port;

    @Value("${wiremock.server.baseUrl}")
    private String wiremockUrl;

    @Karate.Test
    Karate person() {
        return Karate.run(ROOT_PATH + "person.feature")
                .systemProperty("baseUrl", "http://localhost:" + port)
                .systemProperty("wiremockUrl", wiremockUrl);
    }

}
