package com.learning.ai.service;

import com.learning.ai.javabeans.OpenAiRecords;
import com.learning.ai.util.FileUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextToSpeechServiceTest {

    private final TextToSpeechService service = new TextToSpeechService();
    @Test
    void generateMP3() {
        OpenAiRecords.TextToSpeechRequest request = new
                OpenAiRecords.TextToSpeechRequest("tts-1",
                "Namaskara, hegiddira. Manekade yella soukyana", "fable");
        byte[] result = service.generateMP3(request);
        assertNotNull(result, "audio file from openai is blank");
        assertTrue(result.length > 0, "the audio file from openai is blank");

        FileUtils.saveBinaryToFile(result, "test.mp3");
    }
}