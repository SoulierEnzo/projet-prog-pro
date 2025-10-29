package ace.projetprogpro.agent;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Response;
import java.util.List;

public class EvaluationAgent {

    private final OllamaClient ollama;

    public EvaluationAgent(OllamaClient ollama) {
        this.ollama = ollama;
    }

    public String evaluatePerformance(List<Response> responses, String cv, String jobOffer) {
        StringBuilder transcript = new StringBuilder();
        for (Response r : responses) {
            transcript.append("Q: ").append(r.getQuestion() == null ? "?" : r.getQuestion().text()).append('\n')
                    .append("A: ").append(r.getAnswer()).append("\n\n");
        }
        String prompt = "Tu es un recruteur senior. Note la performance globale du candidat (0-100), "
                + "donne une justification en 5 points et des recommandations ciblées.\n"
                + (cv == null ? "" : ("\n[CV]\n" + cv)) + (jobOffer == null ? "" : ("\n[OFFRE]\n" + jobOffer))
                + "\n[TRANSCRIPT]\n" + transcript
                + "\nFormat attendu: JSON avec {score:number, points:[...], recommandations:[...]}";
        return ollama.askModel(prompt);
    }

    public String generateScoreReport(List<Response> responses, String cv, String jobOffer) {
        // alias lisible, si on veut un rapport plus narratif
        return evaluatePerformance(responses, cv, jobOffer);
    }
}