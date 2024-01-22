package openai;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GPT3APIExample {

    public static void main(String[] args) {
        String apiKey = "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";
        String apiUrl = "https://api.openai.com/v1/engines/davinci/completions";

        try {
            String prompt = "Once upon a time in a";
            String response = generateCompletion(apiUrl, apiKey, prompt);
            System.out.println("Generated response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateCompletion(String apiUrl, String apiKey, String prompt) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setDoOutput(true);

        String requestBody = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 50}";

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(requestBody);
        outputStream.flush();
        outputStream.close();

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = responseReader.readLine()) != null) {
            response.append(line);
        }
        responseReader.close();

        return response.toString();
    }
}
