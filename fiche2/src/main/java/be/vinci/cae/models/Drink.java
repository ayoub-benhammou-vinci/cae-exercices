package be.vinci.cae.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "drinks")
public class Drink {
    public Drink() {
    }

    public Drink(String name, String description, float price, Boolean alcoholic, FoodTruck foodTruck) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.alcoholic = alcoholic;
        this.foodTruck = foodTruck;
    }


    //Attributs
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JsonBackReference
    private FoodTruck foodTruck;

    private String description;
    private float price;
    private Boolean alcoholic;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public Boolean getAlcoholic() {
        return alcoholic;
    }

    public Long getId() {
        return id;
    }

    public FoodTruck getFoodTruck() {
        return foodTruck;
    }

    public void setAlcoholic(Boolean alcoholic) {
        this.alcoholic = alcoholic;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFoodTruck(FoodTruck foodTruck) {
        this.foodTruck = foodTruck;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
