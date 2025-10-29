package ace.projetprogpro.agent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Question;
import org.junit.jupiter.api.Test;

class InterviewAgentTest {

    @Test
    void generateQuestion_usesOllama() throws Exception {
        OllamaClient mock = mock(OllamaClient.class);
        when(mock.askModel(anyString())).thenReturn("Pouvez-vous détailler votre dernière expérience ?");

        InterviewAgent agent = new InterviewAgent(mock);
        // préparer la mémoire via réflexion légère ou séquence courte
        // ici on simule une première question directement
        Question q = agentTestHelper_generateOneQuestion(agent);
        assertNotNull(q);
        assertTrue(q.text().toLowerCase().contains("expérience"));
    }

    // petit helper pour accéder à la méthode privée via réflexion (pour test ciblé)
    private Question agentTestHelper_generateOneQuestion(InterviewAgent agent) throws Exception {
        var m = InterviewAgent.class.getDeclaredMethod("generateNextQuestion");
        m.setAccessible(true);
        return (Question) m.invoke(agent);
    }
}