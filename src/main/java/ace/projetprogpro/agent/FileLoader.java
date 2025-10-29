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

public class FileLoader {

    public static void main(String[] args) throws Exception {
        File[] files = getTwoFilesFromUser();
        for (int i = 0; i < files.length; i++) {
            String base64 = encodeFileToBase64(files[i]);
            System.out
                    .println("Fichier " + (i + 1) + " (" + files[i].getName() + ") → base64 length=" + base64.length());
        }
    }

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

    public static String encodeFileToBase64(File file) throws IOException {
        byte[] content = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(content);
    }

    public static String extractText(File file) throws IOException {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".pdf")) {
            try (PDDocument doc = Loader.loadPDF(file)) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(doc);
            }
        }
        // fallback simple: texte brut
        return Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }
}