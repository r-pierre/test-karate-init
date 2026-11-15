package com.arc.gpts.tech.bdddemo.hello;

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
class HelloTest {

    private static final String FEATURES = "src/test/java/com/arc/gpts/tech/bdddemo/hello/";

    @LocalServerPort
    private int port;

    @Value("${wiremock.server.baseUrl}")
    private String wiremockUrl;

    // Karate 2.1.4 fails silently on Windows with "classpath:" / relativeTo() paths
    // (InvalidPathException on ':'), so the features are referenced from the project root.
    @Karate.Test
    Karate hello() {
        return Karate.run(FEATURES + "hello.feature", FEATURES + "wiremock.feature")
                .systemProperty("baseUrl", "http://localhost:" + port)
                .systemProperty("wiremockUrl", wiremockUrl);
    }

}
