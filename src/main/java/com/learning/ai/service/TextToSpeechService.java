package com.learning.ai.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TextToSpeechService {

    public static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    public byte[] generateMP3 (String model, String input, String voice) {

        System.out.println("api_key : " + OPENAI_API_KEY);

        //build HttpRequest
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/audio/speech"))
                .header("Authorization", "Bearer %s".formatted(OPENAI_API_KEY))
                .header("Content-Type", "application/json")
                .header("Accept", "audio/mpeg")
                .POST(HttpRequest.BodyPublishers.ofString(getPayload(model, input, voice)))
                .build();

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<byte[]> httpResponse = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofByteArray());
            return httpResponse.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPayload (String model, String input, String voice) {
        String payload = """
                {
                    "model" : "%s",
                    "input" : "%s",
                    "voice" : "%s"
                }
                """.formatted(model,
                input.replaceAll("\\s+", " ").trim(),
                voice);
        return payload;
    }
}
