package be.vinci.ipl.cae.cae_exercices_fiche3.controllers;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.NewUser;
import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.User;
import be.vinci.ipl.cae.cae_exercices_fiche3.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Récupérer la liste des utilisateurs")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Liste des utilisateurs"),
      @ApiResponse(responseCode = "401", description = "Non autorisé"),
      @ApiResponse(responseCode = "403", description = "Accès interdit")
  })
  @GetMapping({"", "/"})
  public Iterable<User> getAllUsers() {
    return userService.readAllUsers();
  }

  @Operation(summary = "Ajouter un nouvel utilisateur")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Utilisateur créé"),
      @ApiResponse(responseCode = "400", description = "Requête invalide"),
      @ApiResponse(responseCode = "401", description = "Non autorisé")
  })
  @PostMapping({"", "/"})
  public void createUser(@RequestBody NewUser newUser) {
    if(newUser == null || newUser.getUsername() == null || newUser.getUsername().isBlank()
    || newUser.getEmail() == null || newUser.getEmail().isBlank() ||
    newUser.getRole() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    userService.createAnUser(newUser);
  }


}
