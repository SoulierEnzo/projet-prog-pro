package ace.projetprogpro.agent;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Response;

/**
 * Module chargé d'analyser la réponse du candidat après chaque question. Il
 * utilise le modèle pour proposer des axes d'amélioration et un retour
 * constructif.
 */
public class FeedBackModule {

    private final OllamaClient ollama;

    /**
     * Constructeur principal.
     *
     * @param ollama
     *            client utilisé pour interagir avec le modèle d'IA
     */
    public FeedBackModule(OllamaClient ollama) {
        this.ollama = ollama;
    }

    /**
     * Analyse une réponse individuelle du candidat et propose des axes
     * d'amélioration.
     *
     * @param r
     *            la réponse à analyser (inclut la question et la réponse texte)
     * @return une chaîne contenant une analyse synthétique en français
     */
    public String analyzeResponse(Response r) {
        String prompt = "Tu es un coach d'entretien. Analyse la réponse du candidat et propose 3 axes d'amélioration.\n"
                + "Question: " + (r.getQuestion() != null ? r.getQuestion().text() : "?") + "\n"
                + "Réponse du candidat: " + r.getAnswer() + "\n" + "Réponds en français, façon bullet points concis.";
        return ollama.askModel(prompt);
    }
}
