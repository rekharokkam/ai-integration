package com.learning.ai.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learning.ai.javabeans.OpenAiRecords;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.learning.ai.util.CommonConstants.OPENAI_API_KEY;

public class TextToSpeechService {
    private static final String TEXT_TO_SPEECH_URL = "https://api.openai.com/v1/audio/speech";

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public byte[] generateMP3 (OpenAiRecords.TextToSpeechRequest textToSpeechRequest) {
        //build HttpRequest
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(TEXT_TO_SPEECH_URL))
                .header("Authorization", "Bearer %s".formatted(OPENAI_API_KEY))
                .header("Content-Type", "application/json")
                .header("Accept", "audio/mpeg")
                .POST(HttpRequest.BodyPublishers.
                        ofString(gson.toJson(textToSpeechRequest)))
                .build();

        //build HttpClient
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
