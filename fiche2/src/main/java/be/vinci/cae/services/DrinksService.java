package be.vinci.cae.services;

import be.vinci.cae.models.Drink;
import be.vinci.cae.repositories.DrinksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinksService {
    private final DrinksRepository drinksRepository;

    public DrinksService(DrinksRepository drinksRepository) {
        this.drinksRepository = drinksRepository;
    }

    public Iterable<Drink> getAllDrinks(){
        return drinksRepository.findAll();
    }

    public Drink getDrink(long id) {
        return drinksRepository.findById(id).orElse(null);
    }

    public Drink createDrink(Drink drink) {
        drink.setId(null);
        System.out.println(drink);
        return drinksRepository.save(drink);
    }

    public Drink updateDrink(long id, Drink drink){
        System.out.println(id);
        System.out.println(drink);

        //On récupère le Drink grâce à son ID
        Drink drinkUpdate = this.getDrink(id);
        System.out.println(drinkUpdate);

        //Si le Drink existe, on met à jour ses informations
        if(drinkUpdate != null){
            drinkUpdate.setAlcoholic(drink.getAlcoholic());
            drinkUpdate.setPrice(drink.getPrice());
            drinkUpdate.setName(drink.getName());
            drinkUpdate.setDescription(drink.getDescription());
            drinksRepository.save(drinkUpdate);
            return drinkUpdate;
        } else {
            //Si le drink n'existe pas on peut l'ajouter
            System.out.println("here");
            return this.createDrink(drink);
        }
    }

    public void deleteDrink(long id) {
        drinksRepository.deleteById(id);
    }

}
