package be.vinci.cae.controllers;

import be.vinci.cae.models.Drink;
import be.vinci.cae.services.DrinksService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drinks")
public class DrinksController {
    private DrinksService drinkService;

    public DrinksController(DrinksService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/")
    public Iterable<Drink> getDrinks(){
        return drinkService.getAllDrinks();
    }

    @GetMapping("/{id}")
    public Drink getDrink(@PathVariable() String id){
        int idNumber = Integer.parseInt(id);
        return drinkService.getDrink(idNumber);
    }

    @PostMapping
    public Drink createDrink(Drink drink){
        return drinkService.createDrink(drink);
    }

    @PutMapping("/{id}")
    public Drink updateDrink(@RequestBody Drink drink, @PathVariable long id){
        return drinkService.updateDrink(id, drink);
    }
}
