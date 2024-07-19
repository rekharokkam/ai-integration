package com.learning.ai.service;

import com.learning.ai.javabeans.OpenAiRecords;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static com.learning.ai.javabeans.OpenAiRecords.*;
import static org.junit.jupiter.api.Assertions.*;

class OpenAiServiceTest {
    private final OpenAiService openAiService = new OpenAiService();

    @Test
    void listModels () {
        ModelList modelList = openAiService.getModelsList();
        assertNotNull(modelList);

        List<String> models = modelList.data().stream()
                .map(ModelList.Model::id)
                .sorted()
                .toList();
        System.out.println("ChatGPT models: ");
        models.forEach(System.out::println);

        assertTrue(new HashSet<String>(models).containsAll(
                List.of("dall-e-3", "whisper-1", "gpt-4-turbo-2024-04-09", "gpt-4-turbo")
        ));
    }

    @Test
    void ListModelsInFull () {
        List<ModelList.Model> models = openAiService.getModelsList().data();
        models.forEach(System.out::println);
    }
}