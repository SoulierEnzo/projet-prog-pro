package ace.projetprogpro.ui;

import ace.projetprogpro.agent.InterviewAgent;
import ace.projetprogpro.model.Question;

import java.util.Scanner;

public class ConsoleUi {

    private static InterviewAgent agent;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        agent = new InterviewAgent();

        System.out.println("=== Simulateur d'entretien avec IA ===");

        agent.startInterview();
    }

    public boolean askForFeedBack() {
        System.out.println("souhaitez-vous un feedback apres chaque reponse ? (y/n)");
        String reponse = scanner.nextLine();
        return reponse.equals("y");
    }

    public void displayQuestion(Question q) {
        System.out.println(q.getText());
    }
    public String getUserResponse() {
        return scanner.next();
    }
    public void displayFeedback(String feedback) {
        System.out.println("\nvoici quelques conseils pour la reponse que vous avez donne :\n");
        System.out.println(feedback);
    }

    public boolean askToContinue() {
        System.out.println("voulez-vous une autre question ? (y/n)");
        String reponse = scanner.nextLine();
        return  reponse.equals("y");
    }



}
