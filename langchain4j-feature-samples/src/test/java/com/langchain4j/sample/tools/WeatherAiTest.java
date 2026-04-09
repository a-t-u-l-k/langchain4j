package com.langchain4j.sample.tools;

import com.langchain4j.sample.AiModels;
import com.langchain4j.sample.services.Assistant;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;

public class WeatherAiTest {
    private final Assistant assistant = AiServices.builder(Assistant.class)
            .chatLanguageModel(AiModels.OLLAMA_CHAT_MODEL)
            .tools(new OpenWeatherMap())
            .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
            .build();

    @Test
    void testWeather() {
        String question = "What is the weather forecast for Bangalore, India?";
        String answer = assistant.chat(question);
        System.out.println(answer);
    }
}
