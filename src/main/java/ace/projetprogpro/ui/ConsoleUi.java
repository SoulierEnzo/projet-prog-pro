package ace.projetprogpro.ui;

import ace.projetprogpro.agent.InterviewAgent;
import ace.projetprogpro.model.Question;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Interface console pour l'utilisateur du simulateur d'entretien. Cette classe
 * gère l'affichage des questions, la saisie des réponses, l'affichage du
 * feedback et le rapport final.
 */
public class ConsoleUi {

    private static final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    /**
     * Point d'entrée principal pour lancer le simulateur depuis la console.
     *
     * @param args
     *            arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        InterviewAgent agent = new InterviewAgent();
        ConsoleUi ui = new ConsoleUi();
        agent.run(ui);
    }

    /**
     * Affiche le bandeau de bienvenue dans la console.
     */
    public void printBanner() {
        println("=========================");
        println("  Simulateur d'entretien  ");
        println("=========================\n");
    }

    /**
     * Demande à l'utilisateur s'il souhaite recevoir un feedback après chaque
     * réponse.
     *
     * @return true si l'utilisateur souhaite recevoir le feedback, false sinon
     */
    public boolean askFeedbackPreference() {
        println("Souhaitez-vous un feedback après chaque réponse ? (y/n)");
        String rep = scanner.nextLine().trim().toLowerCase();
        return rep.startsWith("y");
    }

    /**
     * Affiche une question à l'utilisateur et invite à saisir la réponse.
     *
     * @param q
     *            la question à afficher
     */
    public void displayQuestion(Question q) {
        println("\nQuestion: " + q.text());
        print("> Votre réponse : ");
    }

    /**
     * Récupère la réponse saisie par l'utilisateur dans la console.
     *
     * @return la réponse de l'utilisateur
     */
    public String getUserResponse() {
        return scanner.nextLine();
    }

    /**
     * Affiche le feedback généré par le simulateur pour une réponse donnée.
     *
     * @param feedback
     *            le texte du feedback à afficher
     */
    public void displayFeedback(String feedback) {
        println("\n— Conseils sur votre réponse —\n" + feedback + "\n");
    }

    /**
     * Demande à l'utilisateur s'il souhaite continuer avec une nouvelle question.
     *
     * @return true si l'utilisateur veut continuer, false sinon
     */
    public boolean askToContinue() {
        println("Voulez-vous une autre question ? (y/n)");
        String rep = scanner.nextLine().trim().toLowerCase();
        return rep.startsWith("y");
    }

    /**
     * Affiche le rapport final de l'entretien.
     *
     * @param report
     *            le texte du rapport final
     */
    public void displayFinalReport(String report) {
        println("\n===== Bilan final =====\n" + report + "\n=======================\n");
    }

    /**
     * Affiche une ligne dans la console.
     *
     * @param s
     *            le texte à afficher
     */
    public void println(String s) {
        System.out.println(s);
    }

    /**
     * Affiche du texte sans saut de ligne dans la console.
     *
     * @param s
     *            le texte à afficher
     */
    public void print(String s) {
        System.out.print(s);
    }

    /**
     * Affiche un message d'erreur dans la console.
     *
     * @param s
     *            le texte du message d'erreur
     */
    public void error(String s) {
        System.err.println(s);
    }
}
