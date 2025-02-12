package be.vinci.cae.services;

import be.vinci.cae.models.FoodTruck;
import be.vinci.cae.repositories.FoodTrucksRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodTrucksService {
    private final FoodTrucksRepository foodTrucksRepository;

    public FoodTrucksService(FoodTrucksRepository foodTrucksRepository) {
        this.foodTrucksRepository = foodTrucksRepository;
    }

    public Iterable<FoodTruck> getAllFoodTrucks(){
        return foodTrucksRepository.findAll();
    }
}
