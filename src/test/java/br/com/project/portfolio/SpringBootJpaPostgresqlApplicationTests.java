package br.com.project.portfolio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Before;

import io.restassured.RestAssured;

@SpringBootTest
class SpringBootJpaPostgresqlApplicationTests {

	@Value("http://localhost:8082")
	private String baseUri;

	@Before
	public void baseBefore() {
		RestAssured.baseURI = baseUri;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
}
