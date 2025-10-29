package ace.projetprogpro.api;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.time.Duration;

public final class OllamaClient {

    private final ChatModel model;

    public OllamaClient(String baseUrl, String modelName, double temperature) {
        this.model = OllamaChatModel.builder().baseUrl(baseUrl).modelName(modelName).temperature(temperature)
                .timeout(Duration.ofSeconds(300000)).build();
    }

    /**
     * Constructeur pratique lisant les variables d'env / system properties, sinon
     * fallback sur application.properties par défaut.
     */
    public OllamaClient() {
        String baseUrl = System.getProperty("ollama.base-url",
                System.getenv().getOrDefault("OLLAMA_BASE_URL", "http://localhost:11434"));
        String modelName = System.getProperty("ollama.model",
                System.getenv().getOrDefault("OLLAMA_MODEL", "llama3.1:8b"));
        double temperature = Double.parseDouble(
                System.getProperty("ollama.temperature", System.getenv().getOrDefault("OLLAMA_TEMPERATURE", "0.3")));
        this.model = OllamaChatModel.builder().baseUrl(baseUrl).modelName(modelName).temperature(temperature)
                .timeout(Duration.ofSeconds(300000)).build();
    }

    public String askModel(String prompt) {
        return model.chat(prompt);
    }
}