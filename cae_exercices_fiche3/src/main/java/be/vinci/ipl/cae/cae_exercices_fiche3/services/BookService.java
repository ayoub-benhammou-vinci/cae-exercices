package be.vinci.ipl.cae.cae_exercices_fiche3.services;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.NewBook;
import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.Book;
import be.vinci.ipl.cae.cae_exercices_fiche3.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Iterable<Book> readAllBooks(String titleContains) {
    if(titleContains == null || titleContains.isEmpty()) return bookRepository.findAll();
    return bookRepository.findByTitleContains(titleContains);
  }

  public Book createBook(NewBook newBook){
    Book book = new Book();

    book.setTitle(newBook.getTitle());
    book.setAuthor(newBook.getAuthor());
    book.setPublishedYear(newBook.getPublishYear());

    return bookRepository.save(book);
  }

  public Book readBookById(Long id){
    return bookRepository.findById(id).orElse(null);
  }

  public Book deleteBook(Long id){
    Book book = this.readBookById(id);
    if (book != null) bookRepository.delete(book);
    return book;
  }


}
