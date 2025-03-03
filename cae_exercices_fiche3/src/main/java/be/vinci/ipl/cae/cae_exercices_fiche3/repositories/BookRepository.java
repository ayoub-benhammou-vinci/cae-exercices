package be.vinci.ipl.cae.cae_exercices_fiche3.repositories;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
  Iterable<Book> findByTitleContains(String titleContains);
}
