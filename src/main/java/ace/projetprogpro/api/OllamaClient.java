package ace.projetprogpro.api;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.time.Duration;

/**
 * Client utilitaire pour interagir avec un modèle Ollama via l'API LangChain4J.
 * Cette classe permet de configurer la connexion au modèle et d'envoyer des requêtes textuelles.
 */
public final class OllamaClient {

    private final ChatModel model;

    /**
     * Constructeur principal.
     * Permet de configurer manuellement le client Ollama.
     *
     * @param baseUrl     l'URL de base du serveur Ollama, par exemple "http://localhost:11434"
     * @param modelName   le nom du modèle à utiliser, par exemple "llama3.1:8b"
     * @param temperature la température du modèle, influençant la créativité des réponses
     */
    public OllamaClient(String baseUrl, String modelName, double temperature) {
        this.model = OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .temperature(temperature)
                .timeout(Duration.ofSeconds(300000))
                .build();
    }

    /**
     * Constructeur par défaut.
     * Tente de lire la configuration depuis les propriétés système ou les variables d'environnement.
     * Si aucune valeur n'est fournie, des valeurs par défaut sont utilisées :
     * URL : "http://localhost:11434"
     * Modèle : "llama3.1:8b"
     * Température : 0.3
     */
    public OllamaClient() {
        String baseUrl = System.getProperty("ollama.base-url",
                System.getenv().getOrDefault("OLLAMA_BASE_URL", "http://localhost:11434"));
        String modelName = System.getProperty("ollama.model",
                System.getenv().getOrDefault("OLLAMA_MODEL", "llama3.1:8b"));
        double temperature = Double.parseDouble(
                System.getProperty("ollama.temperature",
                        System.getenv().getOrDefault("OLLAMA_TEMPERATURE", "0.3")));
        this.model = OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .temperature(temperature)
                .timeout(Duration.ofSeconds(300000))
                .build();
    }

    /**
     * Envoie une requête textuelle (prompt) au modèle Ollama et retourne la réponse.
     *
     * @param prompt le texte de la requête à envoyer au modèle
     * @return la réponse textuelle générée par le modèle
     */
    public String askModel(String prompt) {
        return model.chat(prompt);
    }
}
