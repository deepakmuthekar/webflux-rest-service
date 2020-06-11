/**
 * 
 */
package com.github.deepakmuthekar.books;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.deepakmuthekar.books.api.BookRepresentation;
import com.github.deepakmuthekar.books.repositories.Book;
import com.github.deepakmuthekar.books.services.BooksService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * @author Deepak Muthekar
 *
 */
@Component
@AllArgsConstructor
public class BooksHandler {

	private BooksService booksService;

	public Mono<ServerResponse> get(ServerRequest req) {
		// @formatter:off
		return booksService
		 .findById(req.pathVariable("id"))
		 .flatMap(b -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(b))
		 .switchIfEmpty(ServerResponse.notFound().build());
		// @formatter:on
	}

	public Mono<ServerResponse> create(ServerRequest req) {
		// @formatter:off
		return req.bodyToMono(BookRepresentation.class)
		   .map(new InputValidator()::validate)
		   .flatMap(either ->  {
			  if(either.isLeft()) 
				  return ServerResponse.badRequest().bodyValue(either.getLeft());
			  else 
				 return ServerResponse.ok().body( booksService.save(either.get()), Book.class);
		    });
		// @formatter:on

	}

	public Mono<ServerResponse> list(ServerRequest req) {
		// @formatter:off
		return ServerResponse
		  .ok()
		  .contentType(MediaType.APPLICATION_JSON)
		  .body(fromPublisher(booksService.findAll(), Book.class));		 
		// @formatter:on
	}

	public Mono<ServerResponse> delete(ServerRequest req) {
		// @formatter:off
	    return booksService
	        .findById(req.pathVariable("id"))
	        .flatMap(p -> ServerResponse.noContent().build(booksService.delete(p.getId())))
	        .switchIfEmpty(ServerResponse.notFound().build());
	    // @formatter:on
	  }
}
