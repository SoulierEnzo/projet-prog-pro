package ace.projetprogpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application Spring Boot.
 * Cette classe initialise et démarre le contexte Spring.
 */
@SpringBootApplication
public class ProjetProgProApplication {

    /**
     * Point d'entrée principal de l'application.
     * Lance le contexte Spring Boot.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        SpringApplication.run(ProjetProgProApplication.class, args);
    }
}
