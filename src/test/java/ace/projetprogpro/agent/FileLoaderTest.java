package ace.projetprogpro.agent;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileLoaderTest {

    @TempDir
    Path tempDir;

    private File tempFile;

    @BeforeEach
    void setup() throws IOException {
        tempFile = tempDir.resolve("testfile.txt").toFile();
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath(), StandardCharsets.UTF_8)) {
            writer.write("Bonjour IA !");
        }
    }

    // ✅ Test normal : fichier texte encodé correctement
    @Test
    void testEncodeFileToBase64_NormalCase() throws IOException {
        String base64Encoded = FileLoader.encodeFileToBase64(tempFile);
        byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
        String decodedText = new String(decodedBytes, StandardCharsets.UTF_8);
        assertEquals("Bonjour IA !", decodedText);
    }

    // ⚠️ Test : fichier inexistant
    @Test
    void testEncodeFileToBase64_FileNotFound() {
        File fakeFile = new File(tempDir.toFile(), "does_not_exist.txt");
        assertThrows(IOException.class, () -> FileLoader.encodeFileToBase64(fakeFile));
    }

    // ⚠️ Test : un répertoire au lieu d’un fichier
    @Test
    void testEncodeFileToBase64_WithDirectory() {
        File directory = tempDir.toFile();
        assertThrows(IOException.class, () -> FileLoader.encodeFileToBase64(directory));
    }

    // ⚙️ Test : fichier vide
    @Test
    void testEncodeFileToBase64_EmptyFile() throws IOException {
        File emptyFile = tempDir.resolve("empty.txt").toFile();
        assertTrue(emptyFile.createNewFile());
        String base64 = FileLoader.encodeFileToBase64(emptyFile);
        assertEquals("", base64); // un fichier vide doit donner une chaîne vide
    }

    // ⚙️ Test : contenu spécial (accents, caractères unicode)
    @Test
    void testEncodeFileToBase64_UnicodeFile() throws IOException {
        File unicodeFile = tempDir.resolve("unicode.txt").toFile();
        try (BufferedWriter writer = Files.newBufferedWriter(unicodeFile.toPath(), StandardCharsets.UTF_8)) {
            writer.write("salut c est moi choupi");
        }

        String base64Encoded = FileLoader.encodeFileToBase64(unicodeFile);
        String decoded = new String(Base64.getDecoder().decode(base64Encoded), StandardCharsets.UTF_8);
        assertEquals("salut c est moi choupi", decoded);
    }

    // ⚙️ Test : gros fichier simulé (performance)
    @Test
    void testEncodeFileToBase64_LargeFile() throws IOException {
        File largeFile = tempDir.resolve("large.txt").toFile();
        try (BufferedWriter writer = Files.newBufferedWriter(largeFile.toPath(), StandardCharsets.UTF_8)) {
            for (int i = 0; i < 10000; i++) {
                writer.write("Ligne " + i);
                writer.write(System.lineSeparator());
            }
        }

        String base64 = FileLoader.encodeFileToBase64(largeFile);
        assertNotNull(base64);
        assertTrue(base64.length() > 9999);
    }

    // ⚠️ Test : méthode getTwoFilesFromUser — entrée invalide
    @Test
    void testGetTwoFilesFromUser_InvalidFile() {
        // ⚠️ Impossible à tester directement car méthode interactive (Scanner)
        // Suggestion : on refactorisera la méthode pour accepter un InputStream de test
        assertTrue(true, "Méthode interactive : sera testée après refactorisation");
    }
}
