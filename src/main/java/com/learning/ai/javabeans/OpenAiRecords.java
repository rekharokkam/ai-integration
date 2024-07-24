package com.learning.ai.javabeans;

import com.google.gson.annotations.SerializedName;

import java.awt.*;
import java.util.List;

public class OpenAiRecords {
    public record ModelList (String object, List<Model> data) {
        public record Model (
                String id,
                String object,
                long created,
//                @SerializedName("owned_by")
                String ownedBy){
        }
    }

    public record TextToSpeechRequest (
           String model,
           String input,
           String voice
    ) {
        public TextToSpeechRequest {
            input = input.replaceAll("\\s+", " ").trim();
        }
    }

    public record ImageRequest (
            String model,
            String prompt,
            Integer n,
            String quality,
            String responseFormat,
            String style,
            String size
    ) {}

    public record ImageResponse (
            long created,
            List<Image> data
    ) {
        public record Image (
                String url,
                String revisedPrompt
        ) {}
    }
}
