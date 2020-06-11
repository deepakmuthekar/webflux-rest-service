/**
 * 
 */
package com.github.deepakmuthekar.books;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import com.github.deepakmuthekar.books.api.ApiError;
import com.github.deepakmuthekar.books.api.BookRepresentation;

import io.vavr.control.Either;

/**
 * @author Deepak Muthekar
 *
 */

@Component
public class InputValidator {

	private Validator validator;

	public InputValidator() {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public Either<ApiError, BookRepresentation> validate(BookRepresentation t) {
		Set<ConstraintViolation<BookRepresentation>> violations = this.validator.validate(t);

		// @formatter:off
		if (violations != null && !violations.isEmpty()) {
			List<String> errors = violations
					 .stream()
					 .map(cv -> cv.getMessage())
					 .collect(Collectors.toList());				
			return Either.left(
					ApiError.builder()
					.errors(errors)
					.timestamp(LocalDateTime.now())
					.build());
		} else {
			return Either.right(t);
		}
		// @formatter:on
	}
}
