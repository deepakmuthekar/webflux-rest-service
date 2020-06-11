/**
 * 
 */
package com.github.deepakmuthekar.books.api;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Deepak Muthekar
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BookRepresentation {
	private String id;
	@NotNull(message = "Book Title can not be null")
	private String title;

	@NotNull(message = "At lease one auther should be listed")
	private String author;

	private String isbn;

	private String kind;

}
