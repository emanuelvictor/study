package openai;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.Choice;
import com.azure.ai.openai.models.Completions;
import com.azure.ai.openai.models.CompletionsOptions;
import com.azure.ai.openai.models.NonAzureOpenAIKeyCredential;
import com.azure.core.credential.AzureKeyCredential;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GPT3ReviewGenerator {

    public static void main(String[] args) {
        String apiKey = "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";
        String apiUrl = "https://api.openai.com/v1/engines/davinci/completions";  // URL correta para GPT-3


        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new NonAzureOpenAIKeyCredential(apiKey))
                .endpoint(apiUrl)
                .buildClient();

        List<String> prompt = new ArrayList<>();
        prompt.add("Say this is a test");

        Completions completions = client.getCompletions("gpt-3.5-turbo", new CompletionsOptions(prompt));

        System.out.printf("Model ID=%s is created at %s.%n", completions.getId(), completions.getCreated());
        for (Choice choice : completions.getChoices()) {
            System.out.printf("Index: %d, Text: %s.%n", choice.getIndex(), choice.getText());
        }
    }

    public static String generateReview(String apiUrl, String apiKey, String productDescription) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setDoOutput(true);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", "Generate a product review for: " + productDescription);
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("max_tokens", 5);

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        Gson gson = new Gson();
        outputStream.writeBytes(gson.toJson(requestBody));
        outputStream.flush();
        outputStream.close();

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = responseReader.readLine()) != null) {
            response.append(line);
        }
        responseReader.close();

        return response.toString();
    }
}
