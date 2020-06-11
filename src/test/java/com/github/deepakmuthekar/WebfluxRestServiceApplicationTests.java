package com.github.deepakmuthekar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.deepakmuthekar.books.api.BookRepresentation;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebfluxRestServiceApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	@DisplayName("When create book API invoked with valid book record then book document should be created in database.")
	public void test_insert_book() {
		// @formatter:off
	 webClient.post()
        .uri("/books")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(BookRepresentation
          .builder()
          .title("Inside the Java 2 Virtual Machine")
          .author("Bill Venners")
          .isbn("978-0071350938")
          .kind("paperback")
        .build())
        .exchange()
        .expectStatus().isOk();
 	 // @formatter:on
	}
}
