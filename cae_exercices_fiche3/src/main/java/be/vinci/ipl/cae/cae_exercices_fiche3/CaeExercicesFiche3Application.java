package be.vinci.ipl.cae.cae_exercices_fiche3;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.NewUser;
import be.vinci.ipl.cae.cae_exercices_fiche3.services.BookService;
import be.vinci.ipl.cae.cae_exercices_fiche3.services.UserService;
import me.paulschwarz.springdotenv.DotenvPropertySource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CaeExercicesFiche3Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // Add DotenvPropertySource to environment before registering components
        DotenvPropertySource.addToEnvironment(applicationContext.getEnvironment());
        SpringApplication.run(CaeExercicesFiche3Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserService userService){
        return (args) -> {
            System.out.println("Creating users");
            userService.register("admin","admin");
            userService.register("user", "user");
        };
    }

    @Bean
    public CommandLineRunner demoBook(BookService bookService){
        return (args) -> {
            System.out.println("Creating Books");
        };
    }
}
