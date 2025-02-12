package be.vinci.cae.models;

public class Restaurant {
    private String nom;
    private String typeCuisine;

    public Restaurant(String nom, String typeCuisine) {
        this.nom = nom;
        this.typeCuisine = typeCuisine;
    }

    public Restaurant() {
    }

    public String getNom() {
        return nom;
    }

    public String getTypeCuisine() {
        return typeCuisine;
    }
}
