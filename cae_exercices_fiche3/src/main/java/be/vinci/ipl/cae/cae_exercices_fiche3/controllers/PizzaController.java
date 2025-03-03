package be.vinci.ipl.cae.cae_exercices_fiche3.controllers;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.NewPizza;
import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.Pizza;
import be.vinci.ipl.cae.cae_exercices_fiche3.services.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping({"", "/"})
    public Iterable<Pizza> getPizzas(@RequestParam(required = false) String order) {
        return pizzaService.readAllPizzas(order);
    }
    @Operation(summary = "Get a pizza by its id")
    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable long id) {
        Pizza pizza = pizzaService.readPizzaById(id);
        if (pizza == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return pizza;
    }

    @PostMapping({"", "/"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Pizza addPizza(@RequestBody NewPizza newPizza) {
        if (newPizza == null ||
                newPizza.getTitle() == null ||
                newPizza.getTitle().isBlank() ||
                newPizza.getContent() == null ||
                newPizza.getContent().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return pizzaService.createPizza(newPizza);
    }

    @DeleteMapping("/{id}")
    public Pizza deletePizza(@PathVariable long id) {
        Pizza pizza = pizzaService.deletePizza(id);
        if (pizza == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return pizza;
    }

    @PatchMapping("/{id}")
    public Pizza updatePizza(@PathVariable long id, @RequestBody NewPizza newPizza) {
        if (newPizza == null ||
                (newPizza.getTitle() != null && newPizza.getTitle().isBlank()) ||
                (newPizza.getContent() != null && newPizza.getContent().isBlank())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Pizza pizza = pizzaService.updatePizza(id, newPizza);
        if (pizza == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return pizza;
    }

    @PostMapping("/{id}/order")
    public void orderPizza(@PathVariable long id) {
        if (pizzaService.readPizzaById(id) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // TODO add order logic
    }

}
