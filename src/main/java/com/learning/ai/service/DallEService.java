package com.learning.ai.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.learning.ai.javabeans.OpenAiRecords.*;
import static com.learning.ai.util.CommonConstants.OPENAI_API_KEY;

public class DallEService {
    private static final String TEXT_TO_VIDEO_URL = "https://api.openai.com/v1/images/generations";
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public ImageResponse generateImage (ImageRequest imageRequest) {
        //build HttpRequest
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(TEXT_TO_VIDEO_URL))
                .header("Authorization", "Bearer %s".formatted(OPENAI_API_KEY))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.
                        ofString(gson.toJson(imageRequest)))
                .build();

        //build HttpClient
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("http headers: " + httpResponse.headers());
            System.out.println("http status code: " + httpResponse.statusCode());
            System.out.println("http response : " + httpResponse.body());
            return gson.fromJson(httpResponse.body(), ImageResponse.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
