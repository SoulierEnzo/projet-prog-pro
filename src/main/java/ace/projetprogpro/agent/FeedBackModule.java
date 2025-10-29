package ace.projetprogpro.agent;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Response;

public class FeedBackModule {

    private final OllamaClient ollama;

    public FeedBackModule(OllamaClient ollama) {
        this.ollama = ollama;
    }

    public String analyzeResponse(Response r) {
        String prompt = "Tu es un coach d'entretien. Analyse la réponse du candidat et propose 3 axes d'amélioration.\n"
                + "Question: " + (r.getQuestion() != null ? r.getQuestion().text() : "?") + "\n"
                + "Réponse du candidat: " + r.getAnswer() + "\n" + "Réponds en français, façon bullet points concis.";
        return ollama.askModel(prompt);
    }
}