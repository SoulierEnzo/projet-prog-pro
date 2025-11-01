package ace.projetprogpro.agent;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Scanner;

/**
 * Classe utilitaire permettant de charger, lire et encoder des fichiers.
 * Elle peut extraire le texte d'un fichier PDF ou texte brut,
 * et convertir un fichier en chaîne Base64.
 */
public class FileLoader {

    /**
     * Point d'entrée principal pour tester la classe.
     * Demande deux fichiers à l'utilisateur, les encode en base64
     * et affiche la taille du texte encodé.
     *
     * @param args arguments de la ligne de commande
     * @throws Exception en cas d'erreur d'entrée/sortie
     */
    public static void main(String[] args) throws Exception {
        File[] files = getTwoFilesFromUser();
        for (int i = 0; i < files.length; i++) {
            String base64 = encodeFileToBase64(files[i]);
            System.out.println("Fichier " + (i + 1) + " (" + files[i].getName() + ") → base64 length=" + base64.length());
        }
    }

    /**
     * Demande à l'utilisateur de saisir deux chemins de fichiers valides.
     *
     * @return un tableau contenant les deux fichiers sélectionnés
     * @throws IllegalArgumentException si un des fichiers est invalide
     */
    @SuppressFBWarnings(value = "PATH_TRAVERSAL_IN")
    public static File[] getTwoFilesFromUser() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        File[] files = new File[2];
        for (int i = 0; i < 2; i++) {
            System.out.print("Chemin du fichier " + (i + 1) + " : ");
            String path = scanner.nextLine();
            path = path == null ? "" : path.trim().replace("\"", "");
            File file = new File(path);
            if (!file.exists() || !file.isFile()) {
                throw new IllegalArgumentException("Fichier invalide: " + path);
            }
            files[i] = file;
        }
        return files;
    }

    /**
     * Encode un fichier en chaîne Base64.
     *
     * @param file le fichier à encoder
     * @return le contenu encodé en Base64
     * @throws IOException si une erreur d'accès au fichier survient
     */
    public static String encodeFileToBase64(File file) throws IOException {
        byte[] content = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(content);
    }

    /**
     * Extrait le texte d'un fichier.
     * Si le fichier est un PDF, le texte est extrait via PDFBox.
     * Sinon, le contenu est lu comme un texte brut UTF-8.
     *
     * @param file le fichier à analyser
     * @return le texte extrait
     * @throws IOException si une erreur de lecture survient
     */
    public static String extractText(File file) throws IOException {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".pdf")) {
            try (PDDocument doc = Loader.loadPDF(file)) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(doc);
            }
        }
        return Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }
}
