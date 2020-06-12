/**
 * 
 */
package com.github.deepakmuthekar.books;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author Deepak Muthekar
 *
 */
@Component
public class BooksRouter {

	@Bean
	public RouterFunction<ServerResponse> booksRoute(BooksHandler handler) {
		// @formatter:off
		return RouterFunctions.route()
				.GET("/books", 	      handler::list)
				.GET("/books/{id}",   handler::get)
				.POST("/books",       handler::create)
				.DELETE("/books/{id}",handler::delete)
				.build();
		// @formatter:on
	}
}
