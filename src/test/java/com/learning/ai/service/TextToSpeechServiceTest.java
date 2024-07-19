package com.learning.ai.service;

import com.learning.ai.util.FileUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextToSpeechServiceTest {

    @Test
    void generateMP3() {
        var service = new TextToSpeechService();
        byte[] result = service.generateMP3(
                "tts-1",
                """
                        Namaskara, hegiddira. Manekade yella soukyana
                        """,
                "fable"
        );
        assertNotNull(result, "audio file from openai is blank");
        assertTrue(result.length > 0, "the audio file from openai is blank");

        FileUtils.saveBinaryToFile(result, "test.mp3");
    }
}