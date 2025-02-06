package be.vinci.cae.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    //Data génère des getters et setters, equals et hashcode
    //AllArgsConstructor génère un constructeur avec tous les attributs de classe
    //NoArgsConstrcutor génère un constructeur vide
    String quote;
    String author;
}
