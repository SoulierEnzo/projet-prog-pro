package ace.projetprogpro.agent;

import ace.projetprogpro.api.OllamaClient;
import ace.projetprogpro.model.Question;
import ace.projetprogpro.model.Response;
import ace.projetprogpro.ui.ConsoleUi;

import java.io.File;
import java.io.IOException;

/**
 * Agent principal gérant le déroulement d'un entretien simulé.
 * Il interagit avec l'utilisateur, génère des questions à partir du CV et de l'offre d'emploi,
 * et peut fournir un feedback et un rapport d'évaluation final.
 */
public class InterviewAgent {

    private static final String ERR_FICHIERS = "Problème de récupération des fichiers, veuillez réessayer.";

    private final Memory memory = new Memory();
    private final OllamaClient ollama;
    private final FeedBackModule feedbackModule;
    private final EvaluationAgent evaluationAgent;

    /**
     * Constructeur utilisant un client Ollama fourni.
     *
     * @param ollama le client utilisé pour interagir avec le modèle d'IA
     */
    public InterviewAgent(OllamaClient ollama) {
        this.ollama = ollama;
        this.feedbackModule = new FeedBackModule(ollama);
        this.evaluationAgent = new EvaluationAgent(ollama);
    }

    /**
     * Constructeur par défaut utilisant un client Ollama standard.
     */
    public InterviewAgent() {
        this(new OllamaClient());
    }

    /**
     * Démarre l'entretien interactif dans la console.
     *
     * @param consoleUi interface utilisateur console
     */
    public void run(ConsoleUi consoleUi) {
        consoleUi.printBanner();
        consoleUi.println("Sélectionnez 2 fichiers : (1) votre CV, (2) l'offre d'emploi");
        try {
            File[] files = FileLoader.getTwoFilesFromUser();
            String cvText = FileLoader.extractText(files[0]);
            String offerText = FileLoader.extractText(files[1]);
            memory.setCv(cvText);
            memory.setJobOffer(offerText);
        } catch (IOException e) {
            consoleUi.error(ERR_FICHIERS + " " + e.getMessage());
            return;
        }

        boolean wantsFeedback = consoleUi.askFeedbackPreference();
        boolean keepGoing = true;

        while (keepGoing) {
            Question q = generateNextQuestion();
            consoleUi.displayQuestion(q);
            String userAnswer = consoleUi.getUserResponse();

            Response r = new Response();
            r.setQuestion(q);
            r.setAnswer(userAnswer);
            memory.addResponse(r);

            if (wantsFeedback) {
                String fb = feedbackModule.analyzeResponse(r);
                consoleUi.displayFeedback(fb);
            }

            keepGoing = consoleUi.askToContinue();
        }

        String finalReport = evaluationAgent.generateScoreReport(
                memory.getResponses(),
                memory.getCv(),
                memory.getJobOffer()
        );
        consoleUi.displayFinalReport(finalReport);
        consoleUi.println("Entretien terminé. Merci !");
    }

    /**
     * Génère une nouvelle question d'entretien en se basant sur le contexte (CV, offre, historique).
     *
     * @return une question à poser au candidat
     */
    private Question generateNextQuestion() {
        String prompt = "Tu joues le rôle d'un recruteur. En te basant sur le CV et l'offre d'emploi fournis, "
                + "pose une seule question pertinente pour un entretien, en français.\n"
                + memory.buildConversationContext();
        String q = ollama.askModel(prompt);
        return new Question(q.trim());
    }
}
