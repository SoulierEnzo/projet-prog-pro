package ace.projetprogpro.agent;

import ace.projetprogpro.model.Response;

import java.util.List;

public class Memory {

    private String cv;
    private String jobOffer;

    private List <Response> responses;


    public String getContext() {
        String context;

        context = "CV de l'utilisateur : " + cv;
        context += "\n jobOffer de l'utilisateur : " + jobOffer;
        for ( Response response : responses){
            //TODO chainer sur les response pour lister les question deja posé.
        }
        return null;
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
}
