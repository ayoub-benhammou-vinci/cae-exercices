package be.vinci.ipl.cae.cae_exercices_fiche3.controllers;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.NewBook;
import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.Book;
import be.vinci.ipl.cae.cae_exercices_fiche3.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/books")
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @Operation(summary = "Récupérer la liste des livres")
  @GetMapping({"", "/"})
  @ApiResponse(responseCode = "200", description = "Liste des livres")
  public Iterable<Book> getBooks(
      @Parameter(description = "Filtrer les livres dont le titre contient cette chaîne")
      @RequestParam(required = false) String titleContains){
    return bookService.readAllBooks(titleContains);
  }

  @PostMapping({"", "/"})
  @PreAuthorize("hasRole('ROLE_USER')")
  @Operation(summary = "Ajouter un nouveau livre")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Livre créé"),
      @ApiResponse(responseCode = "400", description = "Requête invalide"),
      @ApiResponse(responseCode = "401", description = "Non autorisé")
  })
  public Book addBook(@RequestBody NewBook newBook){
    if(newBook == null ||
        newBook.getTitle() == null || newBook.getTitle().isBlank() ||
        newBook.getAuthor() == null || newBook.getAuthor().isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    return bookService.createBook(newBook);
  }

  @Operation(summary = "Récupérer un livre par ID")
  @GetMapping("/{id}")
  @ApiResponses({
      @ApiResponse(responseCode = "200",description = "Livre trouvé"),
      @ApiResponse(responseCode = "404", description = "Livre non trouvé")
  })
  public Book getBook(
      @Parameter(description = "ID du livre")
      @PathVariable long id) {
    Book book = bookService.readBookById(id);
    if(book == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return book;
  }

  @Operation(summary = "Supprimer un livre")
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Livre supprimé"),
      @ApiResponse(responseCode = "401", description = "Non autorisé"),
      @ApiResponse(responseCode = "403", description = "Accès interdit"),
      @ApiResponse(responseCode = "404", description = "Livre non trouvé")
  })
  public Book deleteBook(
      @Parameter(description = "ID du livre")
      @PathVariable long id) {
    Book book = bookService.deleteBook(id);
    if(book == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return book;
  }






}
