package ace.projetprogpro.agent;

import ace.projetprogpro.model.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente la mémoire de l'entretien. Contient le texte du CV, l'offre
 * d'emploi, et les réponses du candidat.
 */
public class Memory {

    @Setter
    @Getter
    private String cv;
    @Setter
    @Getter
    private String jobOffer;
    private final List<Response> responses = new ArrayList<>();

    /**
     * Ajoute une réponse à l'historique.
     *
     * @param r
     *            la réponse à ajouter
     */
    public void addResponse(Response r) {
        responses.add(r);
    }

    /**
     * Retourne la liste des réponses enregistrées. Une nouvelle liste est renvoyée
     * pour éviter toute modification externe.
     *
     * @return la liste des réponses
     */
    public List<Response> getResponses() {
        return new ArrayList<>(responses);
    }

    /**
     * Coupe une chaîne si elle dépasse une certaine longueur.
     *
     * @param s
     *            la chaîne d'entrée
     * @param max
     *            la longueur maximale autorisée
     * @return la chaîne tronquée avec une indication si nécessaire
     */
    private static String clip(String s, int max) {
        if (s == null) {
            return "";
        }
        return s.length() <= max ? s : s.substring(0, max) + "...(tronqué)";
    }

    /**
     * Construit le contexte textuel de la conversation, incluant le CV, l'offre
     * d'emploi et l'historique des questions/réponses.
     *
     * @return le texte complet représentant le contexte de l'entretien
     */
    public String buildConversationContext() {
        StringBuilder ctx = new StringBuilder();
        if (cv != null && !cv.isBlank()) {
            ctx.append("\n[CV]\n").append(clip(cv, 6000));
        }
        if (jobOffer != null && !jobOffer.isBlank()) {
            ctx.append("\n[OFFRE]\n").append(clip(jobOffer, 4000));
        }
        if (!responses.isEmpty()) {
            ctx.append("\n[HISTORIQUE]\n");
            for (Response r : responses) {
                if (r.getQuestion() != null) {
                    ctx.append("Q: ").append(clip(r.getQuestion().text(), 400)).append('\n');
                }
                if (r.getAnswer() != null) {
                    ctx.append("A: ").append(clip(r.getAnswer(), 600)).append('\n');
                }
            }
        }
        return ctx.toString();
    }
}
