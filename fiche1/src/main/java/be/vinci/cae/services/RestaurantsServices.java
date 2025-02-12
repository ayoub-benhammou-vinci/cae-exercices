package be.vinci.cae.services;

import be.vinci.cae.models.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantsServices {

    public List<Restaurant> getAll(){
        List<Restaurant> restaurants = new ArrayList<>();

        restaurants.add(new Restaurant("Comme chez soi", "Française"));
        restaurants.add(new Restaurant("Le Chalet de la Forêt","Belge"));
        restaurants.add(new Restaurant("La Villa Lorraine", "Française"));
        restaurants.add(new Restaurant("Belga Queen", "Belge"));
        restaurants.add(new Restaurant("Bia Mara", "Fish"));
        restaurants.add(new Restaurant("Aux Armes de Bruxelles", "Belge"));
        restaurants.add(new Restaurant("Noordzee Mer du Nord", "Poisson"));
        restaurants.add(new Restaurant("Fin de Siècle", "Européenne"));
        restaurants.add(new Restaurant("Bon bon", "Française"));
        restaurants.add(new Restaurant("La Quincaillerie", "Belge"));

        return restaurants;
    }
}
