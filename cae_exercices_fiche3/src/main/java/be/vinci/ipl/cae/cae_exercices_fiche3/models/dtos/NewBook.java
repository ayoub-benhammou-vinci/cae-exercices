package be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos;

import lombok.Data;

@Data
public class NewBook {
  private String title;
  private String author;
  private int publishYear;
}
