package ace.projetprogpro.agent;

import ace.projetprogpro.model.Response;

import java.util.List;

public class Memory {

    private String cv;
    private String jobOffer;

    private List<Response> responses;


    public String getContext() {
        StringBuilder context;

        context = new StringBuilder("CV de l'utilisateur : ").append(cv);
        context.append("\njobOffer de l'utilisateur : ").append(jobOffer);
        context.append("\nQuestions deja poses : ");

        for (Response response : responses) {
            context.append("\n").append(response.getQuestion());
        }

        return context.toString();
    }

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

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
