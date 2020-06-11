/**
 * 
 */
package com.github.deepakmuthekar.books.repositories;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

/**
 * @author Deepak Muthekar
 *
 */
@Data
@Builder
@Document
public class Book {
	@Id
	private String id;

	@NotNull(message = "Book Title can not be null")
	private String title;

	private String author;

	private String isbn;

	private String kind;

}
