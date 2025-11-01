package ace.projetprogpro.agent;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Response;
import java.util.List;

/**
 * Agent responsable de l'évaluation de la performance du candidat à la fin de l'entretien.
 * Il interroge le modèle pour générer une note globale, une justification et des recommandations.
 */
public class EvaluationAgent {

    private final OllamaClient ollama;

    /**
     * Constructeur principal.
     *
     * @param ollama client utilisé pour interagir avec le modèle d'IA
     */
    public EvaluationAgent(OllamaClient ollama) {
        this.ollama = ollama;
    }

    /**
     * Évalue la performance du candidat à partir des réponses fournies, du CV et de l'offre d'emploi.
     * Le modèle doit renvoyer une structure JSON contenant un score, des points d'évaluation et des recommandations.
     *
     * @param responses la liste des réponses du candidat
     * @param cv le texte du CV du candidat (peut être null)
     * @param jobOffer le texte de l'offre d'emploi (peut être null)
     * @return une chaîne JSON contenant le score et les recommandations générées par le modèle
     */
    public String evaluatePerformance(List<Response> responses, String cv, String jobOffer) {
        StringBuilder transcript = new StringBuilder();
        for (Response r : responses) {
            transcript.append("Q: ")
                    .append(r.getQuestion() == null ? "?" : r.getQuestion().text())
                    .append('\n')
                    .append("A: ").append(r.getAnswer())
                    .append("\n\n");
        }
        String prompt = "Tu es un recruteur senior. Note la performance globale du candidat (0-100), "
                + "donne une justification en 5 points et des recommandations ciblées.\n"
                + (cv == null ? "" : ("\n[CV]\n" + cv))
                + (jobOffer == null ? "" : ("\n[OFFRE]\n" + jobOffer))
                + "\n[TRANSCRIPT]\n" + transcript
                + "\nFormat attendu: JSON avec {score:number, points:[...], recommandations:[...]}";
        return ollama.askModel(prompt);
    }

    /**
     * Génère un rapport de score à partir des réponses, du CV et de l'offre.
     * Cette méthode est un alias lisible de {@link #evaluatePerformance(List, String, String)}.
     *
     * @param responses la liste des réponses du candidat
     * @param cv le texte du CV du candidat (peut être null)
     * @param jobOffer le texte de l'offre d'emploi (peut être null)
     * @return une chaîne JSON contenant le rapport d'évaluation
     */
    public String generateScoreReport(List<Response> responses, String cv, String jobOffer) {
        return evaluatePerformance(responses, cv, jobOffer);
    }
}
