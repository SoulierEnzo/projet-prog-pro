package ace.projetprogpro.agent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Response;
import java.util.List;
import org.junit.jupiter.api.Test;

class EvaluationAgentTest {

    @Test
    void evaluatePerformance_returnsJsonLike() {
        OllamaClient mock = mock(OllamaClient.class);
        when(mock.askModel(anyString())).thenReturn(
                "{\"score\":78,\"points\":[\"communication\"],\"recommandations\":[\"exemples chiffrés\"]}");
        EvaluationAgent eval = new EvaluationAgent(mock);

        Response r = new Response();
        r.setQuestion(new ace.projetprogpro.model.Question("Pourquoi cette entreprise ?"));
        r.setAnswer("Valeurs alignées, impact");

        String json = eval.evaluatePerformance(List.of(r), "CV-B64", "JOB-B64");
        assertTrue(json.contains("score"));
        assertTrue(json.contains("recommandations"));
    }
}