package be.vinci.cae.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "foodTrucks")
public class FoodTruck {

    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String adresse;

    @OneToMany(mappedBy = "foodTruck")
    @JsonManagedReference
    private List<Drink> drinks;

    public FoodTruck(String adresse, String nom) {
        this.adresse = adresse;
        this.nom = nom;
    }

    public FoodTruck() {

    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
