package be.vinci.cae.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class Quote {
    //Data génère des getters et setters, equals et hashcode
    //AllArgsConstructor génère un constructeur avec tous les attributs de classe
    //NoArgsConstrcutor génère un constructeur vide
    String quote;
    String author;

    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public Quote() {
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
