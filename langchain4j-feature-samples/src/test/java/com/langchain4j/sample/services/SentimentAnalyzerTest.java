package com.langchain4j.sample.services;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SentimentAnalyzerTest {
//    private final ChatLanguageModel model = OpenAiChatModel.builder()
//            .apiKey(ApiKeys.OPENAI_API_KEY)
//            //.logRequests(true)
//            //.logResponses(true)
//            .build();

    ChatLanguageModel model = OllamaChatModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("llama3.2")
        .logRequests(true)
        .logResponses(true)
        .build();

    private final SentimentAnalyzer sentimentAnalyzer = AiServices.create(
            SentimentAnalyzer.class, model);

    @Test
    void analyzeSentimentOf() {
        System.out.println(sentimentAnalyzer.analyzeSentimentOf("I love Java"));
        System.out.printf(String.valueOf(sentimentAnalyzer.isPositive("I love Java")));
//        assertThat(sentimentAnalyzer.analyzeSentimentOf("I love Java"))
//                .isEqualTo(Sentiment.POSITIVE);
    }

    @Test
    void isPositive() {
        assertThat(sentimentAnalyzer.isPositive("I love Java")).isTrue();
    }
}