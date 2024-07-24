package com.learning.ai.service;

import com.learning.ai.javabeans.OpenAiRecords;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DallEServiceTest {

    private final DallEService dallEService = new DallEService();

    @Test
    void generateImage () {
        var imageRequest = new OpenAiRecords.ImageRequest(
                "dall-e-3",
//                "draw a picture of hanuman with rama and sita showing inside of this heart" +
//                        "that is slit",
                "a beautiful sunset over the ocean ",
                    1,
                "standard" ,
                "url",
                "natural",
                "1024x1024"

        );
        OpenAiRecords.ImageResponse imageResponse = dallEService.generateImage(imageRequest);
        System.out.println(imageResponse.data().getFirst().revisedPrompt());
        System.out.println(imageResponse.data().getFirst().url());
    }

}