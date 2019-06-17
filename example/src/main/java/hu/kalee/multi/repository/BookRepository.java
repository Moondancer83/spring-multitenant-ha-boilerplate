package hu.kalee.multi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

//@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface BookRepository extends CrudRepository<Book, Long> {

}
