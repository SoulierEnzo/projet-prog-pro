package ace.projetprogpro.agent;

import ace.projetprogpro.api.OllamaClient;

import java.io.File;
import java.io.IOException;

public class InterviewAgent {

    private final static String ERREUR_RECUPERATION_FICHIER = "probleme de recuperation des fichiers, veuillez reessayer";

    private Memory memory;

    private OllamaClient ollamaClient;

    public void startInterview() {

        this.memory = new Memory();
        this.ollamaClient = new OllamaClient();

        //recuperation des files de cv et de jobOffer
        File[] files = FileLoader.getTwoFilesFromUser();

        try {
            memory.setCv(FileLoader.encodeFileToBase64(files[0]));
            memory.setJobOffer(FileLoader.encodeFileToBase64(files[1]));
        } catch (IOException | NullPointerException e) {
            System.out.println(ERREUR_RECUPERATION_FICHIER);
        }

    }
    public Object generateQuestion() {
        return null;
    }
    public void processResponse(Object r) { }

}
