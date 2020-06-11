package com.github.deepakmuthekar.books.services;

import org.springframework.stereotype.Service;

import com.github.deepakmuthekar.books.api.BookRepresentation;
import com.github.deepakmuthekar.books.repositories.Book;
import com.github.deepakmuthekar.books.repositories.BooksRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BooksService {
	
	private BooksRepository repository;

	public Mono<Book> save(BookRepresentation br) {
		// @formatter:off
		return repository .save(
				Book.builder()
				 .title(br.getTitle())
				 .author(br.getAuthor())
				 .build());
		// @formatter:on
	}

	public Mono<Book> findById(String bookId) {
		return repository.findById(bookId);
	}

	public Flux<Book> findAll() {
		return repository.findAll();
	}

	public Mono<Void> delete(String bookId) {
		return repository.deleteById(bookId);
	}
}
