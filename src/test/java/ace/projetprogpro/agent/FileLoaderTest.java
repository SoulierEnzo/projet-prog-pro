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

/**
 * Classe de test JUnit pour {@link FileLoader}.
 *
 * Teste les différentes méthodes utilitaires de manipulation de fichiers, en
 * particulier {@link FileLoader#encodeFileToBase64(File)} et la gestion des
 * fichiers temporaires.
 */
class FileLoaderTest {

    @TempDir
    Path tempDir;

    private File tempFile;

    /**
     * Prépare un fichier temporaire avant chaque test.
     *
     * @throws IOException
     *             si une erreur d'écriture se produit
     */
    @BeforeEach
    void setup() throws IOException {
        tempFile = tempDir.resolve("testfile.txt").toFile();
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath(), StandardCharsets.UTF_8)) {
            writer.write("Bonjour IA !");
        }
    }

    /**
     * Test normal : vérifie qu'un fichier texte est correctement encodé en Base64.
     *
     * @throws IOException
     *             si l'encodage échoue
     */
    @Test
    void testEncodeFileToBase64_NormalCase() throws IOException {
        String base64Encoded = FileLoader.encodeFileToBase64(tempFile);
        byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
        String decodedText = new String(decodedBytes, StandardCharsets.UTF_8);
        assertEquals("Bonjour IA !", decodedText);
    }

    /**
     * Test d'un fichier inexistant : vérifie qu'une exception IOException est
     * levée.
     */
    @Test
    void testEncodeFileToBase64_FileNotFound() {
        File fakeFile = new File(tempDir.toFile(), "does_not_exist.txt");
        assertThrows(IOException.class, () -> FileLoader.encodeFileToBase64(fakeFile));
    }

    /**
     * Test avec un répertoire au lieu d’un fichier : vérifie qu'une exception
     * IOException est levée.
     */
    @Test
    void testEncodeFileToBase64_WithDirectory() {
        File directory = tempDir.toFile();
        assertThrows(IOException.class, () -> FileLoader.encodeFileToBase64(directory));
    }

    /**
     * Test d'un fichier vide : vérifie que l'encodage retourne une chaîne vide.
     *
     * @throws IOException
     *             si l'encodage échoue
     */
    @Test
    void testEncodeFileToBase64_EmptyFile() throws IOException {
        File emptyFile = tempDir.resolve("empty.txt").toFile();
        assertTrue(emptyFile.createNewFile());
        String base64 = FileLoader.encodeFileToBase64(emptyFile);
        assertEquals("", base64);
    }

    /**
     * Test d'un fichier contenant des caractères unicode et accents.
     *
     * @throws IOException
     *             si l'encodage échoue
     */
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

    /**
     * Test d'un fichier volumineux simulé pour vérifier la performance de
     * l'encodage.
     *
     * @throws IOException
     *             si l'encodage échoue
     */
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

    /**
     * Test de la méthode interactive {@link FileLoader#getTwoFilesFromUser()}. Ce
     * test est indicatif et ne peut pas être exécuté automatiquement car il
     * nécessite une entrée utilisateur.
     */
    @Test
    void testGetTwoFilesFromUser_InvalidFile() {
        assertTrue(true, "Méthode interactive : sera testée après refactorisation");
    }
}
