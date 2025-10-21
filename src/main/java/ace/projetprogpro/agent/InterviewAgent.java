package ace.projetprogpro.agent;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Question;
import ace.projetprogpro.model.Response;
import ace.projetprogpro.ui.ConsoleUi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InterviewAgent {

    private final static String ERREUR_RECUPERATION_FICHIER = "probleme de recuperation des fichiers, veuillez reessayer";

    private Memory memory;

    private OllamaClient ollamaClient;

    private ConsoleUi consoleUi;

    private boolean feedback;

    private FeedBackModule feedBackModule;

    public void startInterview() {

        this.memory = new Memory();
        this.memory.setResponses(new ArrayList<>());
        this.ollamaClient = new OllamaClient();
        this.consoleUi = new ConsoleUi();
        this.feedBackModule = new FeedBackModule();

        feedback = consoleUi.askForFeedBack();

        //recuperation des files de cv et de jobOffer
        File[] files = FileLoader.getTwoFilesFromUser();

        try {
            memory.setCv(FileLoader.encodeFileToBase64(files[0]));
            memory.setJobOffer(FileLoader.encodeFileToBase64(files[1]));
        } catch (IOException | NullPointerException e) {
            System.out.println(ERREUR_RECUPERATION_FICHIER);
        }



    }
    public Question generateQuestion() {
        // Préparer le prompt à envoyer à Ollama
        String prompt = "Tu es un recruteur dans l'entreprise qui a proposer l'offre d'emploi que je vais te donner ci-dessous." +
                "\n Je veux que tu pose une question en rapport avec cette offre d'emploi ou bien en rapport avec le cv du candidat." +
                "\n Ta question ne doit pas être dans le même contexte qu'une dans la liste des questions fournis plus bas.\n" +
                memory.getContext();

        // Appeler le serveur Ollama via ton service
        String questionText = this.ollamaClient.askModel(prompt);

        return (new Question(questionText));
    }
    public void processResponse() {

        Question question = generateQuestion();

        Response response = new Response();
        response.setQuestion(question);

        consoleUi.displayQuestion(question);

        String textReponse = consoleUi.getUserResponse();

        response.setAnswer(textReponse);

        if (feedback) {
            String textFeedback = feedBackModule.analyzeResponse(response);
            consoleUi.displayFeedback(textFeedback);
        }
    }

}
