package be.vinci.cae.controllers;

import be.vinci.cae.models.FoodTruck;
import be.vinci.cae.services.FoodTrucksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trucks")
public class FoodTrucksController {
    private FoodTrucksService foodTrucksService;

    public FoodTrucksController(FoodTrucksService foodTrucksService) {
        this.foodTrucksService = foodTrucksService;
    }

    @GetMapping("/")
    public Iterable<FoodTruck> getFoodTrucks(){
        return foodTrucksService.getAllFoodTrucks();
    }
}
