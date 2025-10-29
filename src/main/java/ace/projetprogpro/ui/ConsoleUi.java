package ace.projetprogpro.ui;

import ace.projetprogpro.agent.InterviewAgent;
import ace.projetprogpro.model.Question;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleUi {

    private static final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    public static void main(String[] args) {
        InterviewAgent agent = new InterviewAgent();
        ConsoleUi ui = new ConsoleUi();
        agent.run(ui);
    }

    // --------------- helpers ---------------
    public void printBanner() {
        println("=========================");
        println("  Simulateur d'entretien  ");
        println("=========================\n");
    }

    public boolean askFeedbackPreference() {
        println("Souhaitez-vous un feedback après chaque réponse ? (y/n)");
        String rep = scanner.nextLine().trim().toLowerCase();
        return rep.startsWith("y");
    }

    public void displayQuestion(Question q) {
        println("\nQuestion: " + q.text());
        print("> Votre réponse : ");
    }

    public String getUserResponse() {
        return scanner.nextLine();
    }

    public void displayFeedback(String feedback) {
        println("\n— Conseils sur votre réponse —\n" + feedback + "\n");
    }

    public boolean askToContinue() {
        println("Voulez-vous une autre question ? (y/n)");
        String rep = scanner.nextLine().trim().toLowerCase();
        return rep.startsWith("y");
    }

    public void displayFinalReport(String report) {
        println("\n===== Bilan final =====\n" + report + "\n=======================\n");
    }

    // I/O simples
    public void println(String s) {
        System.out.println(s);
    }
    public void print(String s) {
        System.out.print(s);
    }
    public void error(String s) {
        System.err.println(s);
    }
}