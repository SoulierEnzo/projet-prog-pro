package ace.projetprogpro.agent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Question;
import ace.projetprogpro.model.Response;
import org.junit.jupiter.api.Test;

/**
 * Classe de test JUnit pour {@link FeedBackModule}.
 *
 * Vérifie que le module de feedback génère bien des conseils à partir d'une
 * réponse donnée.
 */
class FeedBackModuleTest {

    /**
     * Teste que la méthode {@link FeedBackModule#analyzeResponse(Response)} renvoie
     * des axes d'amélioration attendus dans le feedback.
     */
    @Test
    void analyzeResponse_returnsAdvice() {
        OllamaClient mock = mock(OllamaClient.class);
        when(mock.askModel(anyString()))
                .thenReturn("- Parlez plus des résultats\n- Chiffrez vos impacts\n- Structure STAR");
        FeedBackModule fb = new FeedBackModule(mock);

        Response r = new Response();
        r.setQuestion(new Question("Parlez-moi d'un échec"));
        r.setAnswer("J'ai appris de mes erreurs");

        String out = fb.analyzeResponse(r);
        assertTrue(out.contains("Chiffrez"));
    }
}
