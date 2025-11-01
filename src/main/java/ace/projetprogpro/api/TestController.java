package ace.projetprogpro.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST simple pour tester le backend.
 * Fournit un point d'accès permettant de vérifier que l'application fonctionne correctement.
 */
@RestController
public class TestController {

    /**
     * Endpoint GET de test.
     * Retourne une chaîne indiquant que le backend est opérationnel.
     *
     * @return une chaîne de confirmation ("Backend opérationnel 🚀")
     */
    @GetMapping("/api/test")
    public String test() {
        return "Backend opérationnel 🚀";
    }
}
