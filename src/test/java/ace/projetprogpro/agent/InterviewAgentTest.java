package ace.projetprogpro.agent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Question;
import org.junit.jupiter.api.Test;

/**
 * Classe de test JUnit pour {@link InterviewAgent}.
 *
 * Vérifie que l'agent d'entretien génère correctement une question basée sur la
 * mémoire et les données fournies, en utilisant le client Ollama simulé.
 */
class InterviewAgentTest {

    /**
     * Teste que la méthode privée {@code generateNextQuestion} produit une question
     * cohérente en utilisant le client Ollama.
     *
     * @throws Exception
     *             si la réflexion échoue lors de l'accès à la méthode privée
     */
    @Test
    void generateQuestion_usesOllama() throws Exception {
        OllamaClient mock = mock(OllamaClient.class);
        when(mock.askModel(anyString())).thenReturn("Pouvez-vous detailler votre derniere experience ?");

        InterviewAgent agent = new InterviewAgent(mock);
        // préparer la mémoire via réflexion légère ou séquence courte
        // ici on simule une première question directement
        Question q = agentTestHelper_generateOneQuestion(agent);
        assertNotNull(q);
        assertTrue(q.text().toLowerCase().contains("experience"));
    }

    /**
     * Helper pour accéder à la méthode privée {@code generateNextQuestion} via
     * réflexion et générer une question pour les tests.
     *
     * @param agent
     *            l'instance de {@link InterviewAgent} à tester
     * @return la question générée par la méthode privée
     * @throws Exception
     *             si la réflexion échoue
     */
    private Question agentTestHelper_generateOneQuestion(InterviewAgent agent) throws Exception {
        var m = InterviewAgent.class.getDeclaredMethod("generateNextQuestion");
        m.setAccessible(true);
        return (Question) m.invoke(agent);
    }
}
