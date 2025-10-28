package ace.projetprogpro.agent;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;

public class FileLoader {
    public static void main(String[] args) {
        File[] files = getTwoFilesFromUser();
        try {
            for (int i = 0; i < files.length; i++) {
                String base64Content = encodeFileToBase64(files[i]);
                System.out.println("Fichier " + (i + 1) + " encodé en Base64 :");
                System.out.println(base64Content.substring(0, Math.min(100, base64Content.length())) + "...");
                // Ici tu peux envoyer 'base64Content' à ton API
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer 2 fichiers depuis l'utilisateur
    public static File[] getTwoFilesFromUser() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        File[] files = new File[2];

        for (int i = 0; i < 2; i++) {
            System.out.print("Entrez le chemin du fichier " + (i + 1) + " : ");
            String path = scanner.nextLine();
            File file = new File(FilenameUtils.getName(path));

            if (!file.exists() || !file.isFile()) {
                System.out.println("Le fichier n'existe pas ou n'est pas valide !");
                i--; // Redemander ce fichier
            } else {
                files[i] = file;
            }
        }
        scanner.close();

        return files;
    }

    // Méthode pour encoder n'importe quel fichier en Base64
    public static String encodeFileToBase64(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
