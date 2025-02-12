package be.vinci.cae.controllers;

import be.vinci.cae.models.Drink;
import be.vinci.cae.services.DrinksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
