package be.vinci.cae.controllers;

import be.vinci.cae.models.Restaurant;
import be.vinci.cae.services.RestaurantsServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantsServices restaurantsServices;

    public RestaurantController(RestaurantsServices restaurantsServices) {
        this.restaurantsServices = restaurantsServices;
    }

    @GetMapping("/")
    public Iterable<Restaurant> all(@RequestParam(required = false) String cuisine){
        if(cuisine == null || cuisine.isEmpty()){
            return restaurantsServices.getAll();
        }

        return restaurantsServices.getAll()
                .stream()
                .filter((r) -> r.getTypeCuisine().equalsIgnoreCase(cuisine))
                .toList();
    }
}
