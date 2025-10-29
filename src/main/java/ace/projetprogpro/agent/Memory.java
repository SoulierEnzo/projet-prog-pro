package ace.projetprogpro.agent;

import ace.projetprogpro.model.Response;
import java.util.ArrayList;
import java.util.List;

public class Memory {

    private String cv; // texte / base64
    private String jobOffer; // texte / base64
    private final List<Response> responses = new ArrayList<>();

    public String getCv() {
        return cv;
    }
    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getJobOffer() {
        return jobOffer;
    }
    public void setJobOffer(String jobOffer) {
        this.jobOffer = jobOffer;
    }

    public void addResponse(Response r) {
        responses.add(r);
    }
    public List<Response> getResponses() {
        return new ArrayList<>(responses);
    }

    private static String clip(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max) + "...(tronqué)";
    }

    public String buildConversationContext() {
        StringBuilder ctx = new StringBuilder();
        if (cv != null && !cv.isBlank()) ctx.append("\n[CV]\n").append(clip(cv, 6000));
        if (jobOffer != null && !jobOffer.isBlank()) ctx.append("\n[OFFRE]\n").append(clip(jobOffer, 4000));
        if (!responses.isEmpty()) {
            ctx.append("\n[HISTORIQUE]\n");
            for (Response r : responses) {
                if (r.getQuestion() != null) ctx.append("Q: ").append(clip(r.getQuestion().text(), 400)).append('\n');
                if (r.getAnswer() != null) ctx.append("A: ").append(clip(r.getAnswer(), 600)).append('\n');
            }
        }
        return ctx.toString();
    }

}