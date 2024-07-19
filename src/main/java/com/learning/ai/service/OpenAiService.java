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

import static com.learning.ai.javabeans.OpenAiRecords.*;
import static com.learning.ai.util.CommonConstants.OPENAI_API_KEY;

public class OpenAiService {
    private static final String GET_MODELS_URL = "https://api.openai.com/v1/models";

//    private final Gson gson = new Gson ();
    private final Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
    public ModelList getModelsList () {
        //build HttpRequest
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(GET_MODELS_URL))
                .header("Authorization", "Bearer %s".formatted(OPENAI_API_KEY))
                .header("Accept", "application/json")
                .build();

        //build httpClient
        try (var client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("status code : " + response.statusCode());
            System.out.println("headers : " + response.headers());
            System.out.println("body:  " + response.body());
            return gson.fromJson(response.body(), ModelList.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
