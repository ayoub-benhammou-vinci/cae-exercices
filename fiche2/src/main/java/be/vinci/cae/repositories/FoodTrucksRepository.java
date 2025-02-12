package be.vinci.cae.repositories;

import be.vinci.cae.models.FoodTruck;
import org.springframework.data.repository.CrudRepository;

public interface FoodTrucksRepository extends CrudRepository<FoodTruck, Long> {
}
