package be.vinci.cae.services;

import be.vinci.cae.models.Quote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotesService {

    public List<Quote> getAllQuotes() {
        Quote[] quotes = {
                new Quote("Ce qui est écrit n'est pas ce qui est - c'est juste ce qui est écrit", "Anonyme"),
                new Quote("La vie est un mystère qu'il faut vivre, et non un problème à résoudre", "Gandhi"),
                new Quote("On récolte ce que l'on sème", "Cocotte"),
                new Quote("Méfier-vous du loup qui dort", "Le Petit Chaperon Rouge")
        };
        //Va retourner une ArrayList du tableau statique
        return List.of(quotes);
    }
}
