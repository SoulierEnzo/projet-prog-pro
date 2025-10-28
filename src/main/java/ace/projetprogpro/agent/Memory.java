package ace.projetprogpro.agent;

import ace.projetprogpro.model.Response;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Memory {

    @Getter
    @Setter
    private String cv;

    @Getter
    @Setter
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

    public List<Response> getResponses() {
        return new ArrayList<>(responses);
    }

    public void setResponses(List<Response> responses) {
        this.responses = new ArrayList<>(responses);
    }
}
