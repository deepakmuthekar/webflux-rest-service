/**
 * 
 */
package com.github.deepakmuthekar.books.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Deepak Muthekar
 *
 */
@Repository
public interface BooksRepository extends ReactiveMongoRepository<Book, String>{

}
