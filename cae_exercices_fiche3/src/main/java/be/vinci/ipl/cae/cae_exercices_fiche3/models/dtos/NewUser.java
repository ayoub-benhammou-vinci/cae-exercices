package be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos;

import lombok.Data;

@Data
public class NewUser {
  private String username;
  private String password;
  private String role;
  private String email;
}
